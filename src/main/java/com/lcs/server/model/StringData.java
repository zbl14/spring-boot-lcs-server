package com.lcs.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StringData {
  @JsonProperty("value")
  private String value;

  public StringData() {
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}