package com.example.ssw_322_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ssw_322_project.ClassesAndInterfaces.Form;
import com.example.ssw_322_project.ClassesAndInterfaces.Survey;
import com.example.ssw_322_project.ClassesAndInterfaces.Test;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    Button btnModify, btnViewResults, btnTake;

    FirebaseDatabase database;
    DatabaseReference users, forms, tests , surveys;
    Test focusedTest;
    Survey focusedSurvey;
    boolean focusedOnSurvey;

    private ListView testListView;
    private ArrayAdapter<String> test_adapter;
    private ArrayList<String> test_list_string = new ArrayList<String>();
    private ArrayList<Test> test_arraylist = new ArrayList<Test>();

    private ListView surveyListView;
    private ArrayAdapter<String> survey_adapter;
    private ArrayList<String> survey_list_string = new ArrayList<String>();
    private ArrayList<Survey> survey_arraylist = new ArrayList<Survey>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //Firebase setup
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");
        forms = database.getReference("Forms");
        tests = database.getReference("Tests");
        surveys = database.getReference("Surveys");


        //ListView setup
        test_adapter =  new ArrayAdapter<String>(this,R.layout.activity_listview, test_list_string);
        testListView = (ListView) findViewById(R.id.test_list_view);
        testListView.setAdapter(test_adapter);
        testListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        survey_adapter =  new ArrayAdapter<String>(this,R.layout.activity_listview, survey_list_string);
        surveyListView = (ListView) findViewById(R.id.survey_list_view);
        surveyListView.setAdapter(survey_adapter);
        surveyListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //Home page elements
        btnModify = (Button)findViewById(R.id.btn_modify);
        btnViewResults = (Button)findViewById(R.id.btn_view_results);
        btnTake = (Button)findViewById(R.id.btn_take);

        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(focusedOnSurvey)
                    modifySurvey(focusedSurvey);
                else
                    modifyTest(focusedTest);
            }
        });

        //Loading tests
        tests.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String testName = dataSnapshot.getKey();
                test_list_string.add(testName);

                //Test test = dataSnapshot.getValue(Test.class);
                //test_list_string.add(test.getName());
                //test_arraylist.add(test);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

        //Loading surveys
        surveys.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String surveyName = dataSnapshot.getKey();
                survey_list_string.add(surveyName);
                
                //Survey survey = dataSnapshot.getValue(Survey.class);
                //survey_list_string.add(survey.getName());
                //survey_arraylist.add(survey);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //On click for Tests
        testListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                surveyListView.setItemChecked(-1, true);
                view.setSelected(true);
                focusedTest = test_arraylist.get(position);
                focusedOnSurvey = false;
                Log.d("Clicked on test", ((Test) focusedTest).getName());
            }
        });

        //On click for Surveys
        surveyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                testListView.setItemChecked(-1, true);
                view.setSelected(true);
                focusedSurvey = survey_arraylist.get(position);
                focusedOnSurvey = true;
                Log.d("Clicked on survey", ((Survey) focusedSurvey).getName());
            }
        });



    }

    private void modifySurvey(Survey s){
        Intent intent = new Intent(this, CreateSurveyActivity.class);

        intent.putExtra("Survey", (Survey) focusedSurvey);

        startActivity(intent);

    }

    private void modifyTest(Test t){
        Intent intent = new Intent(this, CreateSurveyActivity.class);

        intent.putExtra("Test", (Test) focusedTest);

        startActivity(intent);
    }
}
