package com.example.BlueCrown.Application.service.UserServices;


import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.BlueCrown.Application.Exceptions.AdminAleradyExist;
import com.example.BlueCrown.Application.Exceptions.AdminNotFound;
import com.example.BlueCrown.Application.Model.UserModel.User;
import com.example.BlueCrown.Application.Repository.UserRepo;
import com.example.BlueCrown.Application.Utility.UtilityService;
/*
 * User Services
 */

@SuppressWarnings("rawtypes")
@Service
public class UserService implements UtilityService<User>, UserDetailsService{

    @Autowired
    private UserRepo Repo;
     @Autowired
      BCryptPasswordEncoder encoder;

    @Override
     public  ResponseEntity<?> saveAdmin(User User) throws AdminAleradyExist {
       if(Repo.existsByEmail(User.getEmail())){
             User.setPassword(encoder.encode(User.getPassword()));
            return new ResponseEntity<>(new AdminAleradyExist("User alerrady existby this Email"),HttpStatus.ALREADY_REPORTED);
        }else{
            Repo.save(User);
            return new ResponseEntity<>(HttpStatus.OK);
        }
      
    }

    @Override
    public User getByEmail(String email){                  
        return Repo.findFirstByEmail(email)
       .orElseThrow(()-> new AdminNotFound("new AdminNotFound(\"User not found by email: \" + adminDTO.getEmail())"));
    }

    @Override
    public Iterator<User> iterator() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }



    @Override
public UserDetails loadUserByUsername(String email) {
    

    User user = getByEmail(email);
    return org.springframework.security.core.userdetails.User.builder()
        .username(user.getEmail())
        .password(user.getPassword())
        .authorities("ROLE_" + user.getUserType())
        .build();
}

    public void UpdateUser(User User) {
    
         Repo.save(User);
      
    }
}


