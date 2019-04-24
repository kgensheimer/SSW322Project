package com.example.ssw_322_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ssw_322_project.ClassesAndInterfaces.Survey;
import com.example.ssw_322_project.ClassesAndInterfaces.Test;

public class TakeTestActivity extends AppCompatActivity {

    TextView title;
    ListView questionListView;
    Button btnFinish;

    Test test;
    ListViewAdapterTakeTestSurvey adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_test);

        Intent intent = getIntent();

        title = (TextView)findViewById(R.id.takeTestText);
        btnFinish = (Button)findViewById(R.id.btnFinishTakingTest);

        test = (Test) intent.getSerializableExtra("Test");

        initListView();




        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome();
            }
        });

    }


    private void initListView(){
        questionListView = (ListView)findViewById(R.id.takeTestListView);
        adapter = new ListViewAdapterTakeTestSurvey(TakeTestActivity.this, test.getQuestionArrayList());
        questionListView.setAdapter(adapter);
    }


    private void returnHome(){
        Intent intent = new Intent(this, LoadCreateButtons.class);
        startActivity(intent);
    }

}
