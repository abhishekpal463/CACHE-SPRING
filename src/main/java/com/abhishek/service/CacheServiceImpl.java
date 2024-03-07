package com.abhishek.service;

import com.abhishek.library.Cache;
import com.abhishek.library.CacheLib.CacheType;
import com.abhishek.library.CacheLib.FifoCache;
import com.abhishek.library.CacheLib.LruCache;
import com.abhishek.library.config.CacheConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService{

  private Cache<Object, Object> cache;

  @Autowired
  public CacheServiceImpl(CacheConfig cacheConfig){
    CacheType cacheType =CacheType.valueOf(cacheConfig.getCacheType().toUpperCase());
    switch (cacheType){
      case LRUCACHE:
        cache = new LruCache<>(cacheConfig.getCapacity());
        break;
      case FIFOCACHE:
        cache = new FifoCache<>(cacheConfig.getCapacity());
        break;
      default:
        throw new RuntimeException("Invalid cache type");
    }
  }

  @Override
  public boolean post(Object key, Object value) {
    return cache.put(key, value);
  }

  @Override
  public Object get(Object key) {
    try{
      return cache.get(key);
    } catch (Exception e){
      System.out.println("[info] CacheServiceImpl: exception:" + e.getMessage() + " key: " + key);
      return null;
    }
  }

  @Override
  public boolean update(Object key, Object value) {
    if (this.get(key) == null) {
      System.out.println("[info] CacheServiceImpl: cache key is not present, key: " + key);
      return false;
    }
    cache.delete(key);
    return cache.put(key, value);
  }

  @Override
  public boolean delete(Object key) {
    try{
      return cache.delete(key);
    } catch (Exception e){
      System.out.println("[info] CacheServiceImpl: cache key is not present key: " + key);
      return false;
    }
  }
}
