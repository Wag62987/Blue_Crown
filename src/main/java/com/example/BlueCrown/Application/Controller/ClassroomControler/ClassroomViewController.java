
package com.example.BlueCrown.Application.Controller.ClassroomControler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.service.ClassroomServices.ClassroomService;


@CrossOrigin("*")
@Controller
@RequestMapping("/Admin/Classroom")
class ClasroomViewController{
  
  @Autowired
  private ClassroomService service;
  // for Add new Classroom
  @PostMapping("/addClassroom")
  public String addClassroom(@ModelAttribute ClassroomModel classroom) {
    System.out.println("Add CLassroom Controller");
    System.out.println(classroom);
    service.addClassroom(classroom);
    return "CreateClassroom";
  }
  @GetMapping("/Update/{id}")
  public String getMethodName(@PathVariable String id,Model model) {
    ClassroomModel classroom=service.getClassroomById(id);
    if(classroom!=null){
    model.addAttribute("classroomId",id);
     model.addAttribute("list", classroom.getNotesList());
     return "UpdateClassroom";
    }
    return "not Found";
  }
  
}

