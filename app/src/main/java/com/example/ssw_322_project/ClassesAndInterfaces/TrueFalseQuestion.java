package com.example.ssw_322_project.ClassesAndInterfaces;

import java.io.Serializable;

public class TrueFalseQuestion extends Question implements Serializable {

    private boolean correctAnswer; //may change to string

    //For Test
    public TrueFalseQuestion(String question, boolean correctAnswer)
    {
        super.setQuestion(question);
        super.setQuestionType("True / False");
        this.correctAnswer = correctAnswer;
    }

    //For Survey
    public TrueFalseQuestion(String question)
    {
        super.setQuestion(question);
        super.setQuestionType("True / False");
    }



    public boolean getCorrectAnswer(){
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer){
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String getAnswerString() {
        if (correctAnswer){
            return "True";
        } else{
            return "False";
        }
    }
}
