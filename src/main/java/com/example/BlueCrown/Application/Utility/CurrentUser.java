package com.example.BlueCrown.Application.Utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.BlueCrown.Application.Model.UserModel.User;
import com.example.BlueCrown.Application.service.UserServices.UserService;

@Service
public class CurrentUser {
    @Autowired 
    private  UserService UserService;
    
    public User getCurrentUser(){
        String email=SecurityContextHolder.getContext().getAuthentication().getName();

              return  UserService.getByEmail(email);

    }
}
