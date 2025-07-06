package com.example.BlueCrown.Application.Config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSuccesHandler implements AuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // TODO Auto-generated method stub
        String Userrole= authentication.getAuthorities().iterator().next().getAuthority();
        System.out.println("Role"+Userrole);
        if(Userrole.equals("ROLE_Admin")){
            response.sendRedirect("/Admin/dashboard");
        }
        else if(Userrole.equals("ROLE_User")){
            response.sendRedirect("/User/dashboard");
        } else{
             response.sendRedirect("/login?error");
        }
    }
    
}
