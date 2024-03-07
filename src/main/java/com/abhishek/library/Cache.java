package com.abhishek.library;

public interface Cache<K, V> {

  boolean put(K k, V v);

  V get(K k);

  boolean delete(K k);

}
