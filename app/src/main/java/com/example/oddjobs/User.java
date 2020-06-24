package com.example.oddjobs;

public class User {

    String email, username, password;
    public User(){ //Default constructor for firebase database

    }

    public User(String email, String username, String password){
        this.email = email;
        this.username = username;
        this.password = password;
    }

    //Getter methods for User Class

    public String getEmail() {
        return email;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    //Setter methods for User Class

    public void setEmail(String email){
        this.email = email;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }




}
