package com.example.BlueCrown.Application.service.NotesService;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.BlueCrown.Application.Model.ClassroomModel.ClassroomModel;
import com.example.BlueCrown.Application.Model.NotesModel.NotesModel;
import com.example.BlueCrown.Application.Repository.ClassroomRepo;
import com.example.BlueCrown.Application.Repository.NotesRepo;
import com.example.BlueCrown.Application.service.ClassroomServices.ClassroomService;

@Service
public class NotesService {
    
    @Autowired
    NotesRepo notesRepo;
    @Autowired
    ClassroomService ClassroomService;
    @Autowired
    ClassroomRepo ClassRepo;
    ///adding notes in Database
    public ResponseEntity<?> saveNotes(NotesModel notes, String id)
    {
       ClassroomModel classroom = ClassroomService.getClassroomById(id);
        if(classroom==null){return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
            System.out.print(classroom);
            notesRepo.save(notes);
            ClassroomService.UpdateClassroom(classroom);
            return new ResponseEntity<>(HttpStatus.CREATED);
            }

    public void DeleteNotes(ClassroomModel classroom) {
        classroom.getNotesList().removeAll(classroom.getNotesList());
    }
}
