package com.example.oddjobs;

public class Post {

    String address, job, description;
    int rate;
    boolean isAccepted;

    public Post(){

    }

    public Post(String address, String job, String description, int rate, boolean isAccepted){
        this.address = address;
        this.job = job;
        this.description = description;
        this.rate = rate;

        this.isAccepted = isAccepted;

    }


    public String getAddress() {
        return address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
