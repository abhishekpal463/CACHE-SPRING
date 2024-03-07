package com.abhishek.entity.request;


import lombok.Getter;

import java.io.Serializable;

@Getter
public class CacheRequest implements Serializable {
  private Object key;
  private Object value;
}
