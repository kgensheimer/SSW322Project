package com.example.ssw_322_project;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.ssw_322_project.ClassesAndInterfaces.MultipleChoiceQuestion;
import com.example.ssw_322_project.ClassesAndInterfaces.TrueFalseQuestion;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class CreateTestActivity extends AppCompatActivity {

    Button btnFinish, btnCreateQuestion, btnDeleteQuestion;
    Button btnMultipleChoice, btnRanking, btnShortAnswer, btnTrueFalse; //Choice of question type

    RadioButton rbtnMCAnswer1, rbtnMCAnswer2, rbtnMCAnswer3, rbtnMCAnswer4;

    MaterialEditText editMCQuestionStr, editMCOption1Str, editMCOption2Str, editMCOption3Str, editMCOption4Str; //multiple choice text fields
    MaterialEditText editTFQuestionStr;

    MaterialEditText editSAQuestionStr;

    ListView questionList;


    FirebaseDatabase database;
    DatabaseReference users, forms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_test);

        //Firebase setup
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");
        forms = database.getReference("Forms");

        //Page elements
        btnCreateQuestion = (Button)findViewById(R.id.btn_create_question_test);
        btnDeleteQuestion = (Button)findViewById(R.id.btn_delete_question_test);
        btnFinish = (Button)findViewById(R.id.btn_finish_test);
        questionList = (ListView)findViewById(R.id.listview_questions_test);

        btnCreateQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQuestion();
            }
        });


    }

    private void createQuestion(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateTestActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();
        View choose_question_type_layout = inflater.inflate(R.layout.question_type_choices, null);

        btnMultipleChoice = (Button)choose_question_type_layout.findViewById(R.id.btn_multiple_choice);
        btnRanking = (Button)choose_question_type_layout.findViewById(R.id.btn_ranking);
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

        //Radio Buttons
        //rbtnMCAnswer1 = create_multiple_choice_test.findViewById(R.id.mc_test_correct_answer1);
        //rbtnMCAnswer2 = create_multiple_choice_test.findViewById(R.id.mc_test_correct_answer2);
        //rbtnMCAnswer3 = create_multiple_choice_test.findViewById(R.id.mc_test_correct_answer3);
        //rbtnMCAnswer4 = create_multiple_choice_test.findViewById(R.id.mc_test_correct_answer4);

        alertDialog.setView(create_multiple_choice_test);
        alertDialog.setTitle("Enter Fields for Multiple Choice Question:");

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

        alertDialog.setView(create_true_false_test);
        alertDialog.setTitle("Enter Field for True/False Question:");

        //Canceling TF question creation

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







                dialog.dismiss();
            }
        });

        alertDialog.show();


    }

    private void createShortAnswer(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateTestActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();
        View create_short_answer_Test = inflater.inflate(R.layout.create_short_answer_test, null);

        editSAQuestionStr = (MaterialEditText)create_short_answer_Test.findViewById(R.id.short_answer_test_question);

        alertDialog.setView(create_short_answer_Test);
        alertDialog.setTitle("Enter Fields for Short Answer Question:");

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

















                dialog.dismiss();
            }
        });

        alertDialog.show();


    }

}


