package com.example.BlueCrown.Application.Model.ClassroomModel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.example.BlueCrown.Application.Model.NotesModel.NotesModel;
import com.mongodb.lang.Nullable;

import jakarta.validation.constraints.NotNull;

public class ClassroomDTO {
    @NotNull
    private String classroomName;
    @NotNull
    private String classroomType;
    private String joinCode;
    @DBRef
    @Nullable
     private List<NotesModel> NotesList=new ArrayList<>();

    public ClassroomDTO(String classroomName, String classroomType, String joinCode,List<NotesModel> NotesList ) {
        this.classroomName = classroomName;
        this.classroomType = classroomType;
        this.joinCode = joinCode;
    }



    public String getJoinCode() {
        return joinCode;
    }
    public void setJoinCode(String joinCode) {
        this.joinCode = joinCode;
    }
   
    public String getClassroomName() {
        return classroomName;
    }
    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }
    public String getClassroomType() {
        return classroomType;
    }
    public void setClassroomType(String classroomType) {
        this.classroomType = classroomType;
    }

    public List<NotesModel> getNotesList() {
        return NotesList;
    }
    public void setNotesList(List<NotesModel> notesList) {
        NotesList = notesList;
    }
  public void ClearNotesList() {
        NotesList.removeAll(NotesList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClassroomDTO{");
        sb.append("classroomName=").append(classroomName);
        sb.append(", classroomType=").append(classroomType);
        sb.append(", joinCode=").append(joinCode);
        sb.append(", NotesList=").append(NotesList);
        sb.append('}');
        return sb.toString();
    }
   
  
    
}


