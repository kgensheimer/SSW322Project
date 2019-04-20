package com.example.ssw_322_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ssw_322_project.ClassesAndInterfaces.MultipleChoiceQuestion;
import com.example.ssw_322_project.ClassesAndInterfaces.Question;
import com.example.ssw_322_project.ClassesAndInterfaces.ShortAnswerQuestion;
import com.example.ssw_322_project.ClassesAndInterfaces.Test;
import com.example.ssw_322_project.ClassesAndInterfaces.TrueFalseQuestion;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class CreateTestActivity extends AppCompatActivity {

    Button btnFinish, btnCreateQuestion, btnDeleteQuestion;
    Button btnMultipleChoice, btnShortAnswer, btnTrueFalse; //Choice of question type

    MaterialEditText editTestName;

    //Multiple Choice elements
    MaterialEditText editMCQuestionStr, editMCOption1Str, editMCOption2Str, editMCOption3Str, editMCOption4Str; //multiple choice text fields
    RadioButton rbtnMCAnswer;
    RadioGroup radioGroupMCAnswer;

    //Short Answer elements
    MaterialEditText editSAQuestionStr;
    MaterialEditText editSAAnswerStr;


    //True False elements
    MaterialEditText editTFQuestionStr;
    RadioButton rbtnTFAnswer;
    RadioGroup radioGroupTFAnswer;

    RecyclerView recyclerView;
    RecyclerViewAdapterTestCreation adapter;

    Test test;

    FirebaseDatabase database;
    DatabaseReference users, tests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_test);

        test = new Test();

        //Firebase setup
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");
        tests = database.getReference("Tests");

        //Page elements
        btnCreateQuestion = (Button)findViewById(R.id.btn_create_question_test);
        btnDeleteQuestion = (Button)findViewById(R.id.btn_delete_question_test);
        btnFinish = (Button)findViewById(R.id.btn_finish_test);
        editTestName = (MaterialEditText)findViewById(R.id.edit_test_name);

        //initialize recycler view
        initRecyclerView();

        btnCreateQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQuestion();
            }
        });

        btnDeleteQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteQuestion();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String testName = editTestName.getText().toString();
                test.setName(testName);

                tests.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                     tests.child(test.getName()).setValue(test);
                     Toast.makeText(CreateTestActivity.this, "Success.", Toast.LENGTH_LONG).show();
                     returnHome();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }


    private void deleteQuestion(){
        Question remove = adapter.getFocusedQuestion();
        if (remove != null)
            test.deleteQuestion(remove);
        initRecyclerView();
    }

    private void createQuestion(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateTestActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();
        View choose_question_type_layout = inflater.inflate(R.layout.question_type_choices, null);

        btnMultipleChoice = (Button)choose_question_type_layout.findViewById(R.id.btn_multiple_choice);
        btnShortAnswer = (Button)choose_question_type_layout.findViewById(R.id.btn_short_answer);
        btnTrueFalse = (Button)choose_question_type_layout.findViewById(R.id.btn_true_false);

        alertDialog.setView(choose_question_type_layout);
        alertDialog.setTitle("Select Question Type:");

        //Canceling question creation
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        alertDialog.show();

        btnMultipleChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMultipleChoice();
            }
        });


        btnTrueFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTrueFalse();
            }
        });

        btnShortAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createShortAnswer();
            }
        });

    }

    private void createMultipleChoice(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateTestActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();
        View create_multiple_choice_test = inflater.inflate(R.layout.create_multiple_choice_test, null);

        editMCQuestionStr = (MaterialEditText)create_multiple_choice_test.findViewById(R.id.mc_test_question);
        editMCOption1Str = (MaterialEditText)create_multiple_choice_test.findViewById(R.id.mc_test_answer1);
        editMCOption2Str = (MaterialEditText)create_multiple_choice_test.findViewById(R.id.mc_test_answer2);
        editMCOption3Str = (MaterialEditText)create_multiple_choice_test.findViewById(R.id.mc_test_answer3);
        editMCOption4Str = (MaterialEditText)create_multiple_choice_test.findViewById(R.id.mc_test_answer4);
        radioGroupMCAnswer = create_multiple_choice_test.findViewById(R.id.mc_test_answer_radio_group);


        alertDialog.setView(create_multiple_choice_test);
        alertDialog.setTitle("Multiple Choice Question:");

        //Canceling MC question creation
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int radioID = radioGroupMCAnswer.getCheckedRadioButtonId();
                rbtnMCAnswer = radioGroupMCAnswer.findViewById(radioID);
                int choiceNumber = radioGroupMCAnswer.indexOfChild(rbtnMCAnswer) + 1;

                final MultipleChoiceQuestion mcq = new MultipleChoiceQuestion(editMCQuestionStr.getText().toString(),
                        editMCOption1Str.getText().toString(),
                        editMCOption2Str.getText().toString(),
                        editMCOption3Str.getText().toString(),
                        editMCOption4Str.getText().toString(),
                        choiceNumber);

                test.addQuestion(mcq);
                initRecyclerView();

                dialog.dismiss();

            }
        });

        alertDialog.show();


    }

    private void createTrueFalse(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateTestActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();
        View create_true_false_test = inflater.inflate(R.layout.create_true_false_test, null);

        editTFQuestionStr = (MaterialEditText)create_true_false_test.findViewById(R.id.tf_test_question);
        radioGroupMCAnswer = create_true_false_test.findViewById(R.id.tf_test_answer_radio_group);

        alertDialog.setView(create_true_false_test);
        alertDialog.setTitle("True/False Question:");

        //Canceling TF question creation

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int radioID = radioGroupMCAnswer.getCheckedRadioButtonId();
                rbtnMCAnswer = radioGroupMCAnswer.findViewById(radioID);
                int choiceNumber = radioGroupMCAnswer.indexOfChild(rbtnMCAnswer) + 1;

                boolean choiceBoolean = false;
                choiceBoolean = (choiceNumber==1); //If its true (choice 1), assign true, else false

                final TrueFalseQuestion tfq = new TrueFalseQuestion(editTFQuestionStr.getText().toString(), choiceBoolean);

                test.addQuestion(tfq);
                initRecyclerView();

                dialog.dismiss();
            }
        });

        alertDialog.show();


    }

    private void createShortAnswer(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateTestActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();
        View create_short_answer_test = inflater.inflate(R.layout.create_short_answer_test, null);

        editSAQuestionStr = (MaterialEditText)create_short_answer_test.findViewById(R.id.short_answer_test_question);
        editSAAnswerStr = (MaterialEditText)create_short_answer_test.findViewById(R.id.short_answer_test_answer);

        alertDialog.setView(create_short_answer_test);
        alertDialog.setTitle("Short Answer Question:");

        //Canceling SA question creation

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

                final ShortAnswerQuestion saq = new ShortAnswerQuestion(editSAQuestionStr.getText().toString(), editSAAnswerStr.getText().toString());

                test.addQuestion(saq);
                initRecyclerView();

                dialog.dismiss();
            }
        });

        alertDialog.show();

    }

    private void initRecyclerView(){
        recyclerView = findViewById(R.id.recyclerview_questions_test);
        adapter = new RecyclerViewAdapterTestCreation(this, test);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void returnHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

}


