package com.example.BlueCrown.Application.Controller.NotesController;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.BlueCrown.Application.Model.NotesModel.NotesModel;

@RequestMapping("/Admin/Classroom")
public class NotesControler {
 
    @PostMapping("/UploadNotes")
    public String UploadNotes(@RequestBody MultipartFile file) throws IOException{
      NotesModel notes=new NotesModel(file.getOriginalFilename(),file.getContentType(),file.getBytes());
      
        return "Done";
    }

    public String DeleteNotes(){
        return "Done";
    }
    
}
