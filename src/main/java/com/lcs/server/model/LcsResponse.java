package com.lcs.server.model;

import java.util.List;

public class LcsResponse {
  private List<StringData> lcs;

  public LcsResponse() {
  }

  public LcsResponse(List<StringData> lcs) {
    this.lcs = lcs;
  }

  public List<StringData> getLcs() {
    return lcs;
  }

  public void setLcs(List<StringData> lcs) {
    this.lcs = lcs;
  }
}
