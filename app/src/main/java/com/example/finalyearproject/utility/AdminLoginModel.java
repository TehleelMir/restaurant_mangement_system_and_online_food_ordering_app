package com.example.finalyearproject.utility;

public class AdminLoginModel {

    String name , email , type , uri;

    public AdminLoginModel() {
    }

    public AdminLoginModel(String name, String email, String type, String uri) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
