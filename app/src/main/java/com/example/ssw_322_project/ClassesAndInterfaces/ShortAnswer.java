package com.example.ssw_322_project.ClassesAndInterfaces;

public class ShortAnswer extends Question
{
    private String input;
    private String answer;

    public ShortAnswer()
    {
    }

    public ShortAnswer (String question, String input, String answer)
    {
        super.setQuestion(question);
        this.input = input;
        this.answer = answer;
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
