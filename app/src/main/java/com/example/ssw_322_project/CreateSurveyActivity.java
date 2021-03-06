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
import android.widget.Toast;

import com.example.ssw_322_project.ClassesAndInterfaces.MultipleChoiceQuestion;
import com.example.ssw_322_project.ClassesAndInterfaces.Question;
import com.example.ssw_322_project.ClassesAndInterfaces.ShortAnswerQuestion;
import com.example.ssw_322_project.ClassesAndInterfaces.Survey;
import com.example.ssw_322_project.ClassesAndInterfaces.TrueFalseQuestion;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class CreateSurveyActivity extends AppCompatActivity {

    Button btnFinish, btnCreateQuestion, btnDeleteQuestion, btnModifyQuestion;
    Button btnMultipleChoice, btnShortAnswer, btnTrueFalse; //Choice of question type

    MaterialEditText editSurveyName;
    MaterialEditText editMCQuestionStr, editMCOption1Str, editMCOption2Str, editMCOption3Str, editMCOption4Str; //multiple choice text fields
    MaterialEditText editTFQuestionStr;
    MaterialEditText editSAQuestionStr;

    RecyclerView recyclerView;
    RecyclerViewAdapterSurveyCreation adapter;


    ListView questionList;

    Survey survey;

    FirebaseDatabase database;
    DatabaseReference users, surveys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_survey);

        Intent intent = getIntent();

        survey = (Survey) intent.getSerializableExtra("Survey");
        String surveyName = null;

        //if no test was passed through/serialized
        if (survey == null)
            survey = new Survey();
        else
            surveyName = survey.getName();



        //Firebase setup
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");
        surveys = database.getReference("Surveys");

        //Page elements
        btnCreateQuestion = (Button)findViewById(R.id.btn_create_question_survey);
        btnDeleteQuestion = (Button)findViewById(R.id.btn_delete_question_survey);
        btnModifyQuestion = (Button)findViewById(R.id.btn_modify_question_survey);
        btnFinish = (Button)findViewById(R.id.btn_finish_survey);
        editSurveyName = (MaterialEditText)findViewById(R.id.edit_survey_name);

        if (surveyName != null)
            editSurveyName.setText(surveyName);


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

        btnModifyQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question modify = adapter.getFocusedQuestion();
                if (modify != null)
                    modifyQuestion(modify);
                initRecyclerView();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String surveyName = editSurveyName.getText().toString();
                survey.setName(surveyName);

                surveys.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        surveys.child(survey.getName()).setValue(survey);
                        Toast.makeText(CreateSurveyActivity.this, "Success.", Toast.LENGTH_LONG).show();
                        returnHome();;

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });



    }


    private void modifyQuestion(Question q){
        if (q.getQuestionType().equals("Multiple Choice")){
            modifyMultipleChoice(q);
        } else if (q.getQuestionType().equals("True / False")){
            modifyTrueFalse(q);
        } else if (q.getQuestionType().equals("Short Answer")){
            modifyShortAnswer(q);
        }
    }

    private void deleteQuestion(){
        Question remove = adapter.getFocusedQuestion();
        if (remove != null)
            survey.deleteQuestion(remove);
        initRecyclerView();
    }

    private void createQuestion(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateSurveyActivity.this);

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
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateSurveyActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();
        View create_multiple_choice_survey = inflater.inflate(R.layout.create_multiple_choice_survey, null);

        editMCQuestionStr = (MaterialEditText)create_multiple_choice_survey.findViewById(R.id.mc_survey_question);
        editMCOption1Str = (MaterialEditText)create_multiple_choice_survey.findViewById(R.id.mc_survey_answer1);
        editMCOption2Str = (MaterialEditText)create_multiple_choice_survey.findViewById(R.id.mc_survey_answer2);
        editMCOption3Str = (MaterialEditText)create_multiple_choice_survey.findViewById(R.id.mc_survey_answer3);
        editMCOption4Str = (MaterialEditText)create_multiple_choice_survey.findViewById(R.id.mc_survey_answer4);

        alertDialog.setView(create_multiple_choice_survey);
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

                final MultipleChoiceQuestion mcq = new MultipleChoiceQuestion(editMCQuestionStr.getText().toString(),
                        editMCOption1Str.getText().toString(),
                        editMCOption2Str.getText().toString(),
                        editMCOption3Str.getText().toString(),
                        editMCOption4Str.getText().toString());

                survey.addQuestion(mcq);
                initRecyclerView();

                dialog.dismiss();

            }
        });

        alertDialog.show();


    }

    private void createTrueFalse(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateSurveyActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();
        View create_true_false_survey = inflater.inflate(R.layout.create_true_false_survey, null);

        editTFQuestionStr = (MaterialEditText)create_true_false_survey.findViewById(R.id.tf_survey_question);

        alertDialog.setView(create_true_false_survey);
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

                final TrueFalseQuestion tfq = new TrueFalseQuestion(editTFQuestionStr.getText().toString());

                survey.addQuestion(tfq);
                initRecyclerView();

                dialog.dismiss();
            }
        });

        alertDialog.show();


    }

    private void createShortAnswer(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateSurveyActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();
        View create_short_answer_survey = inflater.inflate(R.layout.create_short_answer_survey, null);

        editSAQuestionStr = (MaterialEditText)create_short_answer_survey.findViewById(R.id.short_answer_survey_question);

        alertDialog.setView(create_short_answer_survey);
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
                final ShortAnswerQuestion saq = new ShortAnswerQuestion(editSAQuestionStr.getText().toString());

                survey.addQuestion(saq);
                initRecyclerView();

                dialog.dismiss();
            }
        });

        alertDialog.show();


    }

    private void modifyMultipleChoice(final Question q){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateSurveyActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();
        View create_multiple_choice_survey = inflater.inflate(R.layout.create_multiple_choice_survey, null);

        editMCQuestionStr = (MaterialEditText)create_multiple_choice_survey.findViewById(R.id.mc_survey_question);
        editMCOption1Str = (MaterialEditText)create_multiple_choice_survey.findViewById(R.id.mc_survey_answer1);
        editMCOption2Str = (MaterialEditText)create_multiple_choice_survey.findViewById(R.id.mc_survey_answer2);
        editMCOption3Str = (MaterialEditText)create_multiple_choice_survey.findViewById(R.id.mc_survey_answer3);
        editMCOption4Str = (MaterialEditText)create_multiple_choice_survey.findViewById(R.id.mc_survey_answer4);

        alertDialog.setView(create_multiple_choice_survey);
        alertDialog.setTitle("Multiple Choice Question:");

        //Filling in question info into text fields
        editMCQuestionStr.setText(q.getQuestion());
        editMCOption1Str.setText(((MultipleChoiceQuestion) q).getOption1());
        editMCOption2Str.setText(((MultipleChoiceQuestion) q).getOption2());
        editMCOption3Str.setText(((MultipleChoiceQuestion) q).getOption3());
        editMCOption4Str.setText(((MultipleChoiceQuestion) q).getOption4());


        //Canceling MC question modification
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //changing the question object
                q.setQuestion(editMCQuestionStr.getText().toString());
                ((MultipleChoiceQuestion) q).setOption1(editMCOption1Str.getText().toString());
                ((MultipleChoiceQuestion) q).setOption2(editMCOption2Str.getText().toString());
                ((MultipleChoiceQuestion) q).setOption3(editMCOption3Str.getText().toString());
                ((MultipleChoiceQuestion) q).setOption4(editMCOption4Str.getText().toString());

                initRecyclerView();

                dialog.dismiss();

            }
        });

        alertDialog.show();

    }

    private void modifyTrueFalse(final Question q){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateSurveyActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();
        View create_true_false_survey = inflater.inflate(R.layout.create_true_false_survey, null);

        editTFQuestionStr = (MaterialEditText)create_true_false_survey.findViewById(R.id.tf_survey_question);

        alertDialog.setView(create_true_false_survey);
        alertDialog.setTitle("True/False Question:");

        //filling in question info into text field
        editTFQuestionStr.setText(q.getQuestion());

        //Canceling TF question modification
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //changing the question object
                q.setQuestion(editTFQuestionStr.getText().toString());

                initRecyclerView();
                dialog.dismiss();
            }
        });

        alertDialog.show();


    }

    private void modifyShortAnswer(final Question q){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateSurveyActivity.this);

        LayoutInflater inflater = this.getLayoutInflater();
        View create_short_answer_survey = inflater.inflate(R.layout.create_short_answer_survey, null);

        editSAQuestionStr = (MaterialEditText)create_short_answer_survey.findViewById(R.id.short_answer_survey_question);

        alertDialog.setView(create_short_answer_survey);
        alertDialog.setTitle("Short Answer Question:");

        editSAQuestionStr.setText(q.getQuestion());

        //Canceling SA question modification
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
                //changing the question object
                q.setQuestion(editSAQuestionStr.getText().toString());

                initRecyclerView();
                dialog.dismiss();
            }
        });

        alertDialog.show();


    }

    /**
     * Initializes/refreshes the recyclerview to update changes to the test
     */
    private void initRecyclerView(){
        recyclerView = findViewById(R.id.recyclerview_questions_survey);
        adapter = new RecyclerViewAdapterSurveyCreation(this, survey);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void returnHome(){
        Intent intent = new Intent(this, LoadCreateButtons.class);
        startActivity(intent);
    }

}


