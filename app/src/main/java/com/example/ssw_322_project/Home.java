package com.example.ssw_322_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ScrollView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Home extends AppCompatActivity {

    Button btnModify, btnViewResults, btnTake, btnCreate;
    ScrollView tests, surveys;

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
        btnCreate = (Button)findViewById(R.id.btn_create);

    }
}
