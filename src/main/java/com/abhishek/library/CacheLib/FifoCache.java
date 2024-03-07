package com.abhishek.library.CacheLib;

import com.abhishek.library.Cache;
import com.abhishek.library.CacheException;
import lombok.Data;

import java.util.*;

public class FifoCache<K, V> implements Cache<K, V> {

  private final int capacity;

  @Data
  private class Node{
    private final K k;
    private final V v;
  }
  private final Queue<K> queue;
  private final Map<K, V> map;

  public FifoCache(int capacity){
    if(capacity < 1){
      throw new CacheException("capacity cannot be less than 1");
    }
    this.capacity = capacity;
    this.queue = new LinkedList<>();
    this.map = new HashMap<>();
  }
  @Override
  public boolean put(K k, V v) {
    if(k == null || v == null){
      throw new CacheException("key or values cannot be null");
    }
    if(map.containsKey(k)){
      return true;
    }
    if (this.queue.size() == this.capacity){
      K k1 = this.queue.poll();
      map.remove(k1);
    }
    this.queue.offer(k);
    this.map.put(k, v);
    return true;
  }

  @Override
  public V get(K k) {
    if (!map.containsKey(k)){
      throw new CacheException("Key not present");
    }
    return this.map.get(k);
  }

  @Override
  public boolean delete(K k) {
    if (!map.containsKey(k)){
      throw new CacheException("Key not present");
    }
    this.map.remove(k);
    this.queue.remove(k);
    return true;
  }
}
