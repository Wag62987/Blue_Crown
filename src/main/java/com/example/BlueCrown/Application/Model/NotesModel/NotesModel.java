package com.example.BlueCrown.Application.Model.NotesModel;

import org.springframework.data.annotation.Id;

public class NotesModel {
    @Id
    private String Id;
    private String Title;
    private String ContentType;
    private byte[] data;
    public String getId() {
        return Id;
    }
    public void setId(String id) {
        Id = id;
    }
    public NotesModel(String title, String contentType, byte[] data) {
      
        Title = title;
        ContentType = contentType;
        this.data = data;
    }
    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public String getContentType() {
        return ContentType;
    }
    public void setContentType(String contentType) {
        ContentType = contentType;
    }
    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }
}
