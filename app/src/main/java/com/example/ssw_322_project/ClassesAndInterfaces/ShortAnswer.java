package com.example.ssw_322_project.ClassesAndInterfaces;

public class ShortAnswer extends Question
{
    private String input;
    private String answer;

    public ShortAnswer()
    {
    }

    //For Test
    public ShortAnswer (String question, String input, String answer)
    {
        super.setQuestion(question);
        this.input = input;
        this.answer = answer;
    }

    //For Survery
    public ShortAnswer (String question, String input)
    {
        super.setQuestion(question);
        this.input = input;
    }

    public String getCorrectAnswers()
    {
        return this.answer;
    }

    public void setCorrectAnswer(String answer)
    {
        this.answer = answer;
    }
}
