package com.example.BlueCrown.Application.service.NotesService;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.Model.NotesModel.NotesModel;
import com.example.BlueCrown.Application.Repository.NotesRepo;
import com.example.BlueCrown.Application.service.ClassroomServices.ClassroomService;
/*
 * Notes Services
 */
@Service
public class NotesService {
    
    @Autowired
    NotesRepo notesRepo;
    @Autowired
    ClassroomService ClassroomService;
   
    //Adding new Notes
    public ResponseEntity<?> saveNotes(NotesModel notes, String id)
    {
       ClassroomModel classroom = ClassroomService.getClassroomById(id);
       System.out.println(classroom);
        if(classroom==null){return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
            notesRepo.save(notes);
            classroom.getNotesList().add(notes);
            ClassroomService.UpdateClassroom(classroom);
            return new ResponseEntity<>(HttpStatus.CREATED);
            }
        
        // Deleting notes as per Classroom
        public ResponseEntity<?> DeleteNote(String classid, String noteId) {
        NotesModel note = getNoteById(noteId);
        if(note==null){return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        else{
            ClassroomModel classroom=ClassroomService.getClassroomById(classid);
            System.err.println("Claassroom of notes");
            notesRepo.delete(note);
            System.out.println("note before "+classroom.getNotesList());
            classroom.getNotesList().remove(note);
            System.out.println("note before "+classroom.getNotesList());

            ClassroomService.UpdateClassroom(classroom);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    // Geting list of notes as per Classroom
    public List<NotesModel> getNotelist(String classid) {
        ClassroomModel classroom =ClassroomService.getClassroomById(classid);
        if(classroom==null){return null;}
        return classroom.getNotesList();
    }

    // Geting notes By Notes ID
    public NotesModel getNoteById(String id){
        Optional<NotesModel> OPnote= notesRepo.findById(id); 
        if(!OPnote.isPresent())
         return null;
        NotesModel note=OPnote.get();
         return note;
      }
}
