package com.example.ssw_322_project.ClassesAndInterfaces;

import java.util.ArrayList;

public class Survey implements Form {
    private String name;
    private int ID;
    private ArrayList<Question> questionArrayList = new ArrayList<Question>();

    public Survey(){}

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public void addQuestion(Question question) {
        questionArrayList.add(question);
    }

    @Override
    public void deleteQuestion(Question question) {
        questionArrayList.remove(question);
    }

    @Override
    public void tabulateResults() {

    }

    public ArrayList<Question> getQuestionArrayList(){
        return questionArrayList;
    }

    public ArrayList<String> getQuestionStrings(){
        ArrayList<String> questionStrings = new ArrayList();

        for(Question q: questionArrayList){
            questionStrings.add(q.toString());
        }

        return questionStrings;
    }

    public ArrayList<String> getQuestionTypes(){
        ArrayList<String> questionTypes = new ArrayList();

        for(Question q: questionArrayList){
            questionTypes.add(q.getQuestionType());
        }

        return questionTypes;
    }
}

