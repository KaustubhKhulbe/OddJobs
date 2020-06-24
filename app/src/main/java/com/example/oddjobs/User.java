package com.example.oddjobs;

public class User {

    String username, password, address, zipCode;
    public User(){ //Default constructor for firebase database

    }

    public User(String username, String password, String address, String zipCode){

        this.username = username;
        this.password = password;
        this.address = address;
        this.zipCode = zipCode;
    }

    //Getter methods for User Class

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getAddress(){
        return address;
    }

    public String getZipCode(){
        return zipCode;
    }

    //Setter methods for User Class

    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public void setAddress(String address){
        this.address = address;
    }
    public void setZipCode(String zipCode){
        this.zipCode = zipCode;
    }




}
