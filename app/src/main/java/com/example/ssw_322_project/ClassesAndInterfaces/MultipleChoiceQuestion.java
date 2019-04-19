package com.example.ssw_322_project.ClassesAndInterfaces;

public class MultipleChoiceQuestion extends Question {

    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int correctAnswerNumber;


    public MultipleChoiceQuestion(){}

    //Constructor for Survey Question
    public MultipleChoiceQuestion(String question, String option1, String option2, String option3, String option4) {
        super.setQuestion(question);
        super.setQuestionType("Multiple Choice");
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;

    }

    //Constructor for Test Question
    public MultipleChoiceQuestion(String question, String option1, String option2, String option3, String option4, int correctAnswerNumber) {
        super.setQuestion(question);
        super.setQuestionType("Multiple Choice");
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAnswerNumber = correctAnswerNumber;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getCorrectAnswerNumber() {
        return correctAnswerNumber;
    }

    public void setCorrectAnswerNumber(int correctAnswer) {
        this.correctAnswerNumber = correctAnswer;
    }

    @Override
    public String getAnswerString() {

        switch(correctAnswerNumber){
            case 1:
                return option1;
            case 2:
                return option2;
            case 3:
                return option3;
            case 4:
                return option4;
        }

        return null;
    }
}
