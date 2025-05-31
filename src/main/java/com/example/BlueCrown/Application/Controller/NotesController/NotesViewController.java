package com.example.BlueCrown.Application.Controller.NotesController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/Admin/Classroom/{ClassroomId}/Notes")
class NotesViewController{

    @GetMapping("/NotesUploading")
    public String ShowUploadingView(){
    return"UploadNotes";
                                    }
}
