package com.example.BlueCrown.Application.Model.UserModel;

import java.io.Serializable;



public class DTO implements Serializable{
   
   private String email;
    private String username;
   private String userType;
   private int classroom;

   
 public DTO(String email) {
      this.email = email;
     
   }
 public DTO() {
   }

    public DTO(int classroom, String email, String userType, String username) {
        this.classroom = classroom;
        this.email = email;
        this.userType = userType;
        this.username = username;
    }
 public String getEmail() {
    return email;
 }
 public void setEmail(String email) {
    this.email = email;
 }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getClassroom() {
        return classroom;
    }

    public void setClassroom(int classroom) {
        this.classroom = classroom;
    }
 
}
