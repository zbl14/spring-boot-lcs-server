package com.lcs.server.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// TC=O(n^3*k)SC=O(n*k)
// where n is the length of the inputStrings list and k is the maximum length of the strings in inputStrings.

public class LcsAlgorithm {

  public static List<String> findLongestCommonSubstrings(List<String> inputStrings) {
    Set<String> lcsSet = new HashSet<>();
    int minLength = inputStrings.stream().mapToInt(String::length).min().orElse(0);

    for (int len = minLength; len > 0; len--) {
      for (String s : inputStrings) {
        for (int i = 0; i <= s.length() - len; i++) {
          String sub = s.substring(i, i + len);
          if (inputStrings.stream().allMatch(str -> str.contains(sub))) {
            lcsSet.add(sub);
          }
        }
      }
      if (!lcsSet.isEmpty()) {
        break;
      }
    }

    List<String> result = new ArrayList<>(lcsSet);
    result.sort(String::compareTo);
    return result;
  }
}
