package com.example.ssw_322_project.ClassesAndInterfaces;

import java.io.Serializable;

public abstract class Question implements Serializable {

    private String question;
    private int ID;
    private String questionType;

    private String answerString;

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

    public String getQuestionType(){
        return this.questionType;
    }

    public void setQuestionType(String questionType){
        this.questionType = questionType;
    }

    public String toString(){
        return question;
    }

    public abstract String getAnswerString();


}
