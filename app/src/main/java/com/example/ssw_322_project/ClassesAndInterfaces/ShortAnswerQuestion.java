package com.example.ssw_322_project.ClassesAndInterfaces;

public class ShortAnswerQuestion extends Question
{


    private String correctAnswer;

    public ShortAnswerQuestion() {
        super.setQuestionType("Short Answer");
    }

    //For Test
    public ShortAnswerQuestion (String question, String correctAnswer) {
        super.setQuestion(question);
        super.setQuestionType("Short Answer");
        this.correctAnswer = correctAnswer;
    }
    //For Survey
    public ShortAnswerQuestion (String question) {
        super.setQuestion(question);
        super.setQuestionType("Short Answer");
    }

    public String getCorrectAnswer()
    {
        return this.correctAnswer;
    }

    public void setCorrectAnswer(String answer)
    {
        this.correctAnswer = answer;
    }

    @Override
    public String getAnswerString() {
        return correctAnswer;
    }
}
