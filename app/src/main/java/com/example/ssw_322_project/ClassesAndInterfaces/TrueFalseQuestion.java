package com.example.ssw_322_project.ClassesAndInterfaces;

public class TrueFalseQuestion extends Question {

    private boolean correctAnswer; //may change to string
    private final String questionType = "TF";

    //For Test
    public TrueFalseQuestion(String question, boolean correctAnswer)
    {
        super.setQuestion(question);
        this.correctAnswer = correctAnswer;
    }

    //For Survey
    public TrueFalseQuestion(String question)
    {
        super.setQuestion(question);
    }

    public String getQuestionType() {
        return questionType;
    }


    public boolean getCorrectAnswer(){
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer){
        this.correctAnswer = correctAnswer;
    }

}
