package com.example.BlueCrown.Application.Controller.NotesController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.Model.NotesModel.NotesModel;
import com.example.BlueCrown.Application.service.ClassroomServices.ClassroomService;
import com.example.BlueCrown.Application.service.NotesService.NotesService;

import org.springframework.web.bind.annotation.GetMapping;

import com.example.BlueCrown.Application.Exceptions.ClassroomNotFound;

/*
 * Controller for Notes
 */

@RestController

@RequestMapping("/Classroom/{ClassroomId}/Notes")
public class NotesControler {

    @Autowired
    private  NotesService service;
    @Autowired
    private ClassroomService ClassroomService;

    // Adding new Notes in classroom
     @PreAuthorize("hasAnyRole('Admin')")
    @PostMapping("/UploadNotes")
    public ResponseEntity<?> UploadNotes(@RequestParam("file") MultipartFile file,@PathVariable("ClassroomId") String id)throws IOException, ClassroomNotFound{
      NotesModel notes=new NotesModel(file.getOriginalFilename(),file.getContentType(),file.getBytes());  
      ClassroomModel classroom=ClassroomService.getClassroomByCode(id);
     
      if(classroom==null){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
      else{
        service.saveNotes(notes, classroom);
        return new ResponseEntity<>(HttpStatus.CREATED); 
      }
    }
    
    //Geting list of notes of Classroom
     @PreAuthorize("hasAnyRole('Admin','User')")
    @GetMapping()
    public ResponseEntity<List<NotesModel>> getAllNotes(@PathVariable("ClassroomId") String classId) throws ClassroomNotFound{
      List<NotesModel> list=new ArrayList<>();
      if((list=service.getNotelist(classId) )!=null){

        return ResponseEntity.ok(list);
      }
      else{
        return ResponseEntity.ok(list);
      }
    }
  
    //Deleting of notes 
    @PreAuthorize("hasAnyRole('Admin')")
    @DeleteMapping("/{noteid}")
    public ResponseEntity<?> DeleteNote(@PathVariable("ClassroomId") String Classid,@PathVariable("noteid") String noteId) throws ClassroomNotFound{
    
      return  ClassroomService.deleteNote(Classid,noteId);

    }
     @PreAuthorize("hasAnyRole('Admin','User')")
    @GetMapping("/View/{noteId}")
public ResponseEntity<byte[]> viewNote(@PathVariable("ClassroomId") String classId, @PathVariable("noteId") String noteId) {
    NotesModel note = service.getNoteById(noteId); // implement this if missing

    if (note == null) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok()
        .header("Content-Disposition", "inline; filename=\"" + note.getTitle() + "\"")
        .header("Content-Type", note.getContentType())
        .body(note.getData());
}
}
