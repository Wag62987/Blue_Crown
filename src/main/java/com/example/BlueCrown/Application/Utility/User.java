package com.example.BlueCrown.Application.Utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.BlueCrown.Application.Model.AdminModel.Admin;
import com.example.BlueCrown.Application.service.AdminServices.AdminService;

@Component
public class User {
    @Autowired 
    private  AdminService adminService;
    
    public  Admin getCurrentUser(){
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
              return  adminService.getInfoByUsername(username);

    }
}
