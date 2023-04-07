package com.lcs.server.algorithm;

// import java.util.ArrayList;
// import java.util.HashSet;
// import java.util.List;
// import java.util.Set;

// TC = O(n^3 * k)
// SC = O(n * k)
// where n is the length of the inputStrings list and k is the maximum length of the strings in inputStrings.

// public class LcsAlgorithm {

// public static List<String> findLongestCommonSubstrings(List<String>
// inputStrings) {
// Set<String> lcsSet = new HashSet<>();
// int minLength =
// inputStrings.stream().mapToInt(String::length).min().orElse(0);

// for (int len = minLength; len > 0; len--) {
// for (String s : inputStrings) {
// for (int i = 0; i <= s.length() - len; i++) {
// String sub = s.substring(i, i + len);
// if (inputStrings.stream().allMatch(str -> str.contains(sub))) {
// lcsSet.add(sub);
// }
// }
// }
// if (!lcsSet.isEmpty()) {
// break;
// }
// }

// List<String> result = new ArrayList<>(lcsSet);
// result.sort(String::compareTo);
// return result;
// }
// }

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// TC = O(n * k^2), SC = O(n * k^2)
public class LcsAlgorithm {

  public static List<String> findLongestCommonSubstrings(List<String> inputStrings) {

    Map<String, Integer> commonSubstrings = new HashMap<>();

    for (String str : inputStrings) {
      for (int len = 1; len <= str.length(); len++) {
        for (int i = 0; i <= str.length() - len; i++) {
          String sub = str.substring(i, i + len);
          commonSubstrings.put(sub, commonSubstrings.getOrDefault(sub, 0) + 1);
        }
      }
    }

    Set<String> lcsSet = new HashSet<>();
    int maxOccurrences = 0;

    for (Map.Entry<String, Integer> entry : commonSubstrings.entrySet()) {
      if (entry.getValue() == inputStrings.size()) {
        String sub = entry.getKey();
        if (sub.length() > maxOccurrences) {
          maxOccurrences = sub.length();
          lcsSet.clear();
          lcsSet.add(sub);
        } else if (sub.length() == maxOccurrences) {
          lcsSet.add(sub);
        }
      }
    }

    List<String> result = new ArrayList<>(lcsSet);
    result.sort(String::compareTo);
    return result;
  }
}
