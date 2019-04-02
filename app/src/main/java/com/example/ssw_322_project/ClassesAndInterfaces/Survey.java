package com.example.ssw_322_project.ClassesAndInterfaces;


import java.util.ArrayList;

public class Survey implements Form {

    private String name;
    private int ID;
    private ArrayList<Question> questionArrayList = new ArrayList<Question>();

    public Survey(){}

    @Override
    public void addQuestion(Question question)
    {
        questionArrayList.add(question);
    }

    @Override
    public void deleteQuestion() {

    }

    @Override
    public void tabulateResults()
    {

    }

    public ArrayList<Question> getQuestionArrayList(){
        return questionArrayList;
    }
}

