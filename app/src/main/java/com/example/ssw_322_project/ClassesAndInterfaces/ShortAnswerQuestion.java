package com.example.ssw_322_project.ClassesAndInterfaces;

public class ShortAnswerQuestion extends Question
{


    private String correctAnswer;
    private final String questionType = "SA";

    public ShortAnswerQuestion() {}

    //For Test
    public ShortAnswerQuestion (String question, String correctAnswer) {
        super.setQuestion(question);
        this.correctAnswer = correctAnswer;
    }
    //For Survey
    public ShortAnswerQuestion (String question) {
        super.setQuestion(question);
    }



    public String getQuestionType() {
        return questionType;
    }

    public String getCorrectAnswer()
    {
        return this.correctAnswer;
    }

    public void setCorrectAnswer(String answer)
    {
        this.correctAnswer = answer;
    }

}
