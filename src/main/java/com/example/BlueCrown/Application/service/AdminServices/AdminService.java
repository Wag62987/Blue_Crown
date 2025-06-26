package com.example.BlueCrown.Application.service.AdminServices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BlueCrown.Application.Exceptions.AdminAleradyExist;
import com.example.BlueCrown.Application.Exceptions.AdminNotFound;
import com.example.BlueCrown.Application.Model.AdminModel.Admin;
import com.example.BlueCrown.Application.Repository.AdminRepo;
/*
 * Admin Services
 */

@Service
public class AdminService implements UserDetailsService{

    @Autowired
    private AdminRepo Repo;
 
    //////////Creating new user/////////
    @Transactional
    public  void saveAdmin(Admin admin) throws AdminAleradyExist {
      if(!Repo.existsByEmail(admin.getEmail())){

         BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
         admin.setPassword(encoder.encode(admin.getPassword()));
         Repo.save(admin);
      }
      else{
         throw new AdminAleradyExist("Admin alerady Exist");
      }
      
    }
    ///for get edmin
    public  Admin getByEmail(String email){                  
       return Repo.findFirstByEmail(email)
       .orElseThrow(()-> new AdminNotFound("new AdminNotFound(\"Admin not found by email: \" + adminDTO.getEmail())"));
    }
   

    @Override
public UserDetails loadUserByUsername(String email) {
    

    Admin admin = getByEmail(email);
    return User.builder()
        .username(admin.getEmail())
        .password(admin.getPassword())
        .build();
}

    public void UpdateAdmin(Admin admin) {
      if(Repo.existsById(admin.getId())){
         Repo.save(admin);
      }else{
         throw new AdminNotFound("Admin Not Found");
      }
    }
}

