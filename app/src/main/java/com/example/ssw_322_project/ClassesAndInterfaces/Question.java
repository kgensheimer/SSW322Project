package com.example.ssw_322_project.ClassesAndInterfaces;

public abstract class Question {

    private String question;
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public String getQuestion(){
        return this.question;
    }


}
