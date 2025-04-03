package com.example.BlueCrown.Application.Model.AdminModel;

import java.io.Serializable;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;


public class AdminDTO implements Serializable{
   
 public AdminDTO(String email, String password) {
      this.email = email;
      Password = password;
   }
 public AdminDTO() {
   }
 private String email;
 private String Password;
 public String getEmail() {
    return email;
 }
 public void setEmail(String email) {
    this.email = email;
 }
 public String getPassword() {
    return Password;
 }
 public void setPassword(String password) {
    Password = password;
 }

}
