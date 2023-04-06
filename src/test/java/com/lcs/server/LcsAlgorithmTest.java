package com.lcs.server;

import com.lcs.server.algorithm.LcsAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LcsAlgorithmTest {

  @Test
  void findLongestCommonSubstringsTest1() {
    List<String> inputStrings = Arrays.asList("comcast", "communicate", "commutation");
    List<String> expectedOutput = Arrays.asList("com");

    List<String> actualOutput = LcsAlgorithm.findLongestCommonSubstrings(inputStrings);

    assertEquals(expectedOutput, actualOutput);
  }

  void findLongestCommonSubstringsTest2() {
    List<String> inputStrings = Arrays.asList("comcastbbbbdeeee", "comcasticbbbbeeee", "broadcasterbbbbeeee");
    List<String> expectedOutput = Arrays.asList("bbbb", "cast", "eeee");

    List<String> actualOutput = LcsAlgorithm.findLongestCommonSubstrings(inputStrings);

    assertEquals(expectedOutput, actualOutput);
  }
}