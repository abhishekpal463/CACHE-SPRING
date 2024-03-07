package com.abhishek.library.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "cache.properties")
public class CacheConfig {

  private Integer capacity;
  private String cacheType;
}
