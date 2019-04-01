package com.example.ssw_322_project.ClassesAndInterfaces;

public class TrueFalseQuestion extends Question {

    private boolean correctAnswer; //may change to string

    public TrueFalseQuestion(String question, boolean correctAnswer){
        super.setQuestion(question);
        this.correctAnswer = correctAnswer;
    }

    public boolean getCorrectAnswer(){
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer){
        this.correctAnswer = correctAnswer;
    }

}
