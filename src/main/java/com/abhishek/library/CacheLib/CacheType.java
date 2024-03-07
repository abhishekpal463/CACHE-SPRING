package com.abhishek.library.CacheLib;

public enum CacheType {
  LRUCACHE("LRUCACHE"),
  FIFOCACHE("FIFOCACHE");

  private String name;

  CacheType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
