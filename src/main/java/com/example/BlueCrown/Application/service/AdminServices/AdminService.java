package com.example.BlueCrown.Application.service.AdminServices;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.BlueCrown.Application.Exceptions.AdminNotFound;
import com.example.BlueCrown.Application.Model.AdminModel.*;
import com.example.BlueCrown.Application.Repository.AdminRepo;
/*
 * Admin Services
 */

@Service
@Component
public class AdminService implements UserDetailsService{

    @Autowired
    private AdminRepo Repo;

    //////////Creating new user/////////
    public  void saveAdmin(Admin Admin) {
       Repo.save(Admin);
    }
    ///for get edmin
    public  Admin getByEmail(String email){                  
       return Repo.findByEmail(email)
       .orElseThrow(()-> new AdminNotFound("new AdminNotFound(\"Admin not found by email: \" + adminDTO.getEmail())"));
    }
    public Admin getInfoByUsername(String username){
       Optional<Admin> ad=Repo.findBy(username);
       return ad.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {        
         Optional<Admin> opadmin=Repo.findBy(username);
         Admin admin=opadmin.get();
         if(admin!=null)
         { 
            return User.builder()
            .username(admin.getUsername())
            .password(new BCryptPasswordEncoder().encode(admin.getPassword()))
            .build();
         }
         else{
            throw new UsernameNotFoundException("User not found");
         }
    }
}

