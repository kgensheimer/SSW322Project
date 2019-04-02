package com.example.ssw_322_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.ssw_322_project.ClassesAndInterfaces.Question;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

public class Home extends AppCompatActivity {

    Button btnModify, btnViewResults, btnTake, btnCreateSurvey, btnCreateTest;

    FirebaseDatabase database;
    DatabaseReference users, forms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Firebase setup
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");
        forms = database.getReference("Forms");

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
