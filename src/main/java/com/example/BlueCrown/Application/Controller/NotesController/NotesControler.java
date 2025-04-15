package com.example.BlueCrown.Application.Controller.NotesController;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.BlueCrown.Application.Model.NotesModel.NotesModel;
import com.example.BlueCrown.Application.service.NotesService.NotesService;

@RestController
@RequestMapping("/Admin/Classroom")
@Service
public class NotesControler {
 @Autowired
 NotesService service;
    @PostMapping("{id}/UploadNotes")
    public ResponseEntity<?> UploadNotes(@RequestParam("file") MultipartFile file,@PathVariable String id) throws IOException{
      NotesModel notes=new NotesModel(file.getOriginalFilename(),file.getContentType(),file.getBytes());  
      service.saveNotes(notes,id);  
        return new ResponseEntity<>(HttpStatus.CREATED);
     
    }

    public String DeleteNotes(){
        return "Done";
    }
    
}
