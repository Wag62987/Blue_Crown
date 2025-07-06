
package com.example.BlueCrown.Application.Controller.ClassroomControler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@CrossOrigin("*")
@Controller
@RequestMapping("/Admin/Classroom")
class ClasroomViewController{
  
  @Autowired
  // for Add new Classroom
  @GetMapping("/CreateClassroom")
  public String CreateClassroom() {
    return "CreateClassroom";
  }
 
  
}

