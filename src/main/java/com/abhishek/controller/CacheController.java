package com.abhishek.controller;

import com.abhishek.entity.constant.ApiPath;
import com.abhishek.entity.request.CacheRequest;
import com.abhishek.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = ApiPath.CACHE)
public class CacheController {

  @Autowired
  private CacheService cacheService;

  @PostMapping
  ResponseEntity<Boolean> postCache(@RequestBody CacheRequest cacheRequest){
    boolean isSuccess = cacheService.post(cacheRequest.getKey(), cacheRequest.getValue());
    return ResponseEntity.ok(isSuccess);
  }

  @GetMapping(path = "/{key}")
  ResponseEntity<Object> getCache(@PathVariable Object key){
    return ResponseEntity.ok(cacheService.get(key));
  }

  @PutMapping(path = "/update")
  ResponseEntity<Boolean> updateCache(@RequestBody CacheRequest cacheRequest) {
    return ResponseEntity.ok(cacheService.update(cacheRequest.getKey(), cacheRequest.getValue()));
  }

  @DeleteMapping(path = "/delete/{key}")
  ResponseEntity<Boolean> deleteKey(@PathVariable Object key){
    return  ResponseEntity.ok(cacheService.delete(key));
  }
}
