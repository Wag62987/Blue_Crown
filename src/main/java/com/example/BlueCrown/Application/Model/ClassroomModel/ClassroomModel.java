package com.example.BlueCrown.Application.Model.ClassroomModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.BlueCrown.Application.Model.AdminModel.Admin;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(value="Classrooms")
@TypeAlias("classroom")
public class ClassroomModel {
    @Id
    private String classroomId;
    private String classroomName;
    private String classroomType;
  
    public String getClassroomName() {
        return classroomName;
    }
    public String getClassroomId() {
        return classroomId;
    }
    public void setClassroomId(String classroomId) {
        classroomId = classroomId;
    }
    public void setClassroomName(String classroomName) {
        classroomName = classroomName;
    }
    public String getClassroomType() {
        return classroomType;
    }
    public void setClassroomType(String classroomType) {
        classroomType = classroomType;
    }
    @Override
    public String toString() {
        return "ClassroomModel [ClassroomName=" + classroomName + ", ClassroomType=" + classroomType;
    }
  
    
}
