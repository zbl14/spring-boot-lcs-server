package com.lcs.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcs.server.algorithm.LcsAlgorithm;
import com.lcs.server.model.LcsResponse;
import com.lcs.server.model.StringData;
import com.lcs.server.model.StringDataSet;
import com.lcs.server.model.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lcs")
public class LcsController {

  @PostMapping
  public ResponseEntity<Object> findLongestCommonSubstring(@RequestBody(required = false) StringDataSet stringDataSet,
      HttpServletRequest request) {
    if (stringDataSet == null || stringDataSet.getSetOfStrings() == null) {
      return buildErrorResponse(HttpStatus.BAD_REQUEST, "Request body is missing or in incorrect format.", request);
    }

    if (stringDataSet.getSetOfStrings().isEmpty()) {
      return buildErrorResponse(HttpStatus.BAD_REQUEST, "setOfStrings should not be empty.", request);
    }

    Set<String> uniqueStrings = stringDataSet.getSetOfStrings().stream().map(StringData::getValue)
        .collect(Collectors.toSet());
    if (uniqueStrings.size() != stringDataSet.getSetOfStrings().size()) {
      return buildErrorResponse(HttpStatus.BAD_REQUEST, "setOfStrings must be a Set (unique values only).", request);
    }

    List<String> lcsList = LcsAlgorithm.findLongestCommonSubstrings(new ArrayList<>(uniqueStrings));
    List<StringData> lcsStringDataList = lcsList.stream().map(str -> {
      StringData sd = new StringData();
      sd.setValue(str);
      return sd;
    }).collect(Collectors.toList());

    LcsResponse lcsResponse = new LcsResponse(lcsStringDataList);
    return ResponseEntity.status(HttpStatus.OK).body(lcsResponse);
  }

  private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String message, HttpServletRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(
        LocalDateTime.now(),
        status.value(),
        status.getReasonPhrase(),
        message,
        request.getRequestURI());
    return ResponseEntity.status(status).body(errorResponse);
  }
}
