package com.example.BlueCrown.Application.Utility;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.example.BlueCrown.Application.Exceptions.AdminAleradyExist;

public interface UtilityService<T> extends Iterable<T>{

     @Transactional
 ResponseEntity<?> saveAdmin(T obj) throws AdminAleradyExist;
      public  T getByEmail(String email);
    
}
