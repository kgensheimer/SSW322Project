package com.example.ssw_322_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.ssw_322_project.ClassesAndInterfaces.Form;
import com.example.ssw_322_project.ClassesAndInterfaces.Question;
import com.example.ssw_322_project.ClassesAndInterfaces.Survey;
import com.example.ssw_322_project.ClassesAndInterfaces.Test;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    Button btnModify, btnViewResults, btnTake, btnCreateSurvey, btnCreateTest;

    FirebaseDatabase database;
    DatabaseReference users, forms, tests , surveys;
    Form focusedForm;

    private ListView testList;
    private ArrayAdapter<String> test_adapter;
    private ArrayList<String> test_list = new ArrayList<String>();

    private ListView surveyList;
    private ArrayAdapter<String> survey_adapter;
    private ArrayList<String> survey_list = new ArrayList<String>();



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
        test_adapter =  new ArrayAdapter<String>(this,R.layout.activity_listview,test_list);
        testList = (ListView) findViewById(R.id.test_list_view);
        testList.setAdapter(test_adapter);
        testList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        survey_adapter =  new ArrayAdapter<String>(this,R.layout.activity_listview,survey_list);
        surveyList = (ListView) findViewById(R.id.survey_list_view);
        surveyList.setAdapter(survey_adapter);
        surveyList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //Home page elements
        btnModify = (Button)findViewById(R.id.btn_modify);
        btnViewResults = (Button)findViewById(R.id.btn_view_results);
        btnTake = (Button)findViewById(R.id.btn_take);
        btnCreateSurvey = (Button)findViewById(R.id.btn_create_survey);
        btnCreateTest = (Button)findViewById(R.id.btn_create_test);

        btnCreateSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSurvey();
            }
        });

        btnCreateTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTest();
            }
        });

        //Loading tests
        tests.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value= dataSnapshot.getKey();
                test_list.add(value);
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
                String value= dataSnapshot.getKey();
                survey_list.add(value);
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
        testList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                surveyList.setItemChecked(-1, true);
                view.setSelected(true);
            }
        });

        //On click for Surveys
        surveyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                testList.setItemChecked(-1, true);
                view.setSelected(true);
            }
        });

    }



    private void createSurvey(){
        Intent intent = new Intent(this, CreateSurveyActivity.class);
        startActivity(intent);
    }


    private void createTest(){
        Intent intent = new Intent(this, CreateTestActivity.class);
        startActivity(intent);
    }








}
