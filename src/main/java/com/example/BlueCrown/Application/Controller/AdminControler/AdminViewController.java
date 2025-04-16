package com.example.BlueCrown.Application.Controller.AdminControler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;


@Controller
public class AdminViewController {
    
    @GetMapping("/")
    public String loginPage() {
        return "index";
    }

    @GetMapping("/Admin/Home")
    public String getHome(HttpSession session) {
       return null;
    }
    
}
