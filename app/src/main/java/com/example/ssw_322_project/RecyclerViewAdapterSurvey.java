package com.example.ssw_322_project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ssw_322_project.ClassesAndInterfaces.Survey;
import com.example.ssw_322_project.ClassesAndInterfaces.Test;

import java.util.ArrayList;

public class RecyclerViewAdapterSurvey extends RecyclerView.Adapter<RecyclerViewAdapterSurvey.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapterTest";

    private Survey survey;
    private Context mContext;

    private ArrayList<String> questionStrings = new ArrayList<String>();
    //private ArrayList<String> questionTypeStrings = new ArrayList<String>();

    public RecyclerViewAdapterSurvey(Context mContext, Survey survey) {
        this.survey = survey;
        this.mContext = mContext;
        this.questionStrings = this.survey.getQuestionStrings();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclerview_test, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.question.setText(questionStrings.get(i));
    }

    @Override
    public int getItemCount() {
        return questionStrings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView question;
        TextView questionType;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.textViewQuestionT);
            questionType = itemView.findViewById(R.id.textViewQuestionTypeT);
            parentLayout = itemView.findViewById(R.id.parent_layout_test);

        }
    }
}
