package com.example.BlueCrown.Application.Model.ClassroomModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.BlueCrown.Application.Model.AdminModel.AdminModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(value="Classrooms")
@TypeAlias("classroom")
public class ClassroomModel {
    @Id
    @JsonProperty("classroomId") // Ensure JSON mapping if needed
    private String classroomId;
    @JsonProperty("classroomName")
    private String classroomName;
    @JsonProperty("classroomType")
    private String classroomType;
    @JsonManagedReference
    private AdminModel admin;
    public ClassroomModel(String classroomId, String classroomName, String classroomType, AdminModel admin) {
        this.classroomId = classroomId;
        classroomName = classroomName;
        this.classroomType = classroomType;
        this.admin = admin;
    }
    public String getClassroomName() {
        return classroomName;
    }
    public String getClassroomId() {
        return classroomId;
    }
    public void setClassroomId(String classroomId) {
        classroomId = classroomId;
    }
    public AdminModel getAdmin() {
        return admin;
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
    public AdminModel getModel() {
        return admin;
    }
    @Override
    public String toString() {
        return "ClassroomModel [ClassroomName=" + classroomName + ", ClassroomType=" + classroomType + ", model="
                + admin + "]";
    }
    public  void setAdmin(AdminModel admin) {
        this.admin = admin;
    }
  
    
}
