package com.abhishek.library.CacheLib;

import com.abhishek.library.Cache;
import com.abhishek.library.CacheException;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class LruCache<K, V> implements Cache<K, V> {

  @Data
  private class Node{
    private final K key;
    private final V value;
    Node pre = null;
    Node next = null;
  }

  private final Map<K, Node> map = new HashMap<>();

  private final int capacity;

  Node head;
  Node tail;

  public LruCache(int capacity){
    if(capacity < 1){
      throw new CacheException("capacity cannot be less than 1");
    }
    this.capacity = capacity;
  }

  @Override
  public boolean put(K k, V v) {
    if(k == null || v == null){
      throw new CacheException("key or values cannot be null");
    }
    if(map.containsKey(k)){
      this.removeNode(map.get(k));
    }
    this.add(k, v);
    return true;
  }

  @Override
  public V get(K k) {
    if(map.containsKey(k)) {
      Node node = map.get(k);
      V v = node.value;
      removeNode(node);
      add(k, v);
      return v;
    } else {
      throw new CacheException("Key not present");
    }
  }

  @Override
  public boolean delete(K k) {
    if(map.containsKey(k)){
      Node node = map.get(k);
      removeNode(node);
      return true;
    } else {
      throw new CacheException("Key not present");
    }
  }

  private void removeNode(Node node){
    K k = node.key;
    if(node.next == null && node.pre == null){
      head = null;
      tail = null;
    } else if(node.next == null) {
      tail = node.pre;
      node.pre = null;
      tail.next = null;
    } else if(node.pre == null){
      head = node.next;
      node.next = null;
      head.pre = null;
    } else {
      node.pre.next = node.next;
      node.next.pre = node.pre;
      node.next = null;
      node.pre = null;
    }
    map.remove(k);
  }

  private void add(K k, V v){
    if(map.size() == capacity){
      this.removeNode(tail);
    }

    if (map.size() == 0){
      head = new Node(k, v);
      head.pre = null;
      head.next = null;
      tail = head;
    } else {
      Node node = new Node(k, v);
      node.next = head;
      node.pre = null;
      head.pre = node;
      head = node;
    }

    map.put(k, head);
  }
}
