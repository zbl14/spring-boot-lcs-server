package com.lcs.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcs.server.controller.LcsController;
import com.lcs.server.model.ErrorResponse;
import com.lcs.server.model.StringData;
import com.lcs.server.model.StringDataSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LcsController.class)
public class LcsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  private StringDataSet stringDataSet;

  @BeforeEach
  public void setUp() {
    stringDataSet = new StringDataSet();
  }

  @Test
  public void testMissingRequestBody() throws Exception {
    ResultActions resultActions = mockMvc.perform(post("/lcs")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());

    ErrorResponse errorResponse = parseErrorResponse(resultActions);
    assertEquals("Request body is missing or in incorrect format.", errorResponse.getMessage());
  }

  @Test
  public void testEmptySetOfStrings() throws Exception {
    stringDataSet.setSetOfStrings(Set.of());

    ResultActions resultActions = mockMvc.perform(post("/lcs")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(stringDataSet)))
        .andExpect(status().isBadRequest());

    ErrorResponse errorResponse = parseErrorResponse(resultActions);
    assertEquals("setOfStrings should not be empty.", errorResponse.getMessage());
  }

  @Test
  public void testNonUniqueSetOfStrings() throws Exception {
    StringData string1 = new StringData();
    string1.setValue("comcast");
    StringData string2 = new StringData();
    string2.setValue("comcast");

    Set<StringData> nonUniqueSet = new HashSet<>(Arrays.asList(string1, string2));
    stringDataSet.setSetOfStrings(nonUniqueSet);

    ResultActions resultActions = mockMvc.perform(post("/lcs")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(stringDataSet)))
        .andExpect(status().isBadRequest());

    ErrorResponse errorResponse = parseErrorResponse(resultActions);
    assertEquals("setOfStrings must be a Set (unique values only).", errorResponse.getMessage());
  }

  private ErrorResponse parseErrorResponse(ResultActions resultActions) throws Exception {
    String responseBody = resultActions.andReturn().getResponse().getContentAsString();
    return objectMapper.readValue(responseBody, ErrorResponse.class);
  }

}
