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

public class LoadCreateButtons extends AppCompatActivity {

    Button btnCreateSurvTest, btnLoadSurvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_create_buttons);

       btnCreateSurvTest = (Button) findViewById(R.id.btnCreateTestSurvey);
       btnLoadSurvTest = (Button) findViewById(R.id.btnLoadTestSurvey);

       btnCreateSurvTest.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openCreateSurveyTestPage();
           }
       });

       btnLoadSurvTest.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v)
           {
               openLoadSurveyTestPage();
           }
       });


    }
    public void openCreateSurveyTestPage()
    {
        Intent intent = new Intent(this, CreateSurveyTestButtons.class);
        startActivity(intent);
    }

    public void openLoadSurveyTestPage()
    {
       Intent intent = new Intent(this,Home.class);
       startActivity(intent);
    }
}

