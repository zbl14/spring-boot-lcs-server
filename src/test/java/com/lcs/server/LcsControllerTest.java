package com.lcs.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcs.server.controller.LcsController;
import com.lcs.server.model.StringData;
import com.lcs.server.model.StringDataSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    mockMvc.perform(post("/lcs")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Request body is missing or in incorrect format."));
  }

  @Test
  public void testEmptySetOfStrings() throws Exception {
    stringDataSet.setSetOfStrings(Set.of());

    mockMvc.perform(post("/lcs")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(stringDataSet)))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("setOfStrings should not be empty."));
  }

  @Test
  public void testNonUniqueSetOfStrings() throws Exception {
    StringData string1 = new StringData();
    string1.setValue("comcast");
    StringData string2 = new StringData();
    string2.setValue("comcast");

    Set<StringData> nonUniqueSet = new HashSet<>(Arrays.asList(string1, string2));
    stringDataSet.setSetOfStrings(nonUniqueSet);

    mockMvc.perform(post("/lcs")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(stringDataSet)))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("setOfStrings must be a Set (unique values only)."));
  }
}
