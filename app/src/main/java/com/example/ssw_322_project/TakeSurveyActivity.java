package com.example.ssw_322_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssw_322_project.ClassesAndInterfaces.Survey;
import com.example.ssw_322_project.ClassesAndInterfaces.Test;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class TakeSurveyActivity extends AppCompatActivity {

    TextView title;
    ListView questionListView;
    Button btnFinish;

    Survey survey;
    ListViewAdapterTakeTestSurvey adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_survey);

        Intent intent = getIntent();

        title = (TextView)findViewById(R.id.takeSurveyText);
        btnFinish = (Button)findViewById(R.id.btnFinishTakingSurvey);

        survey = (Survey) intent.getSerializableExtra("Survey");

        initListView();




        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome();
            }
        });

    }


    private void initListView(){
        questionListView = (ListView)findViewById(R.id.takeSurveyListView);
        adapter = new ListViewAdapterTakeTestSurvey(TakeSurveyActivity.this, survey.getQuestionArrayList());
        questionListView.setAdapter(adapter);
    }


    private void returnHome(){
        Intent intent = new Intent(this, LoadCreateButtons.class);
        startActivity(intent);
    }


}
