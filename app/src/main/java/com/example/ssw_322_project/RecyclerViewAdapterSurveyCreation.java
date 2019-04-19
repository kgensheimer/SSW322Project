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

import java.util.ArrayList;

public class RecyclerViewAdapterSurveyCreation extends RecyclerView.Adapter<RecyclerViewAdapterSurveyCreation.ViewHolder> {

    private static final String TAG = "AdapterSurveyCreation";

    private Survey survey;
    private Context mContext;

    private ArrayList<String> questionStrings = new ArrayList<String>();
    private ArrayList<String> questionTypes = new ArrayList<String>();

    public RecyclerViewAdapterSurveyCreation(Context mContext, Survey survey) {
        this.survey = survey;
        this.mContext = mContext;
        this.questionStrings = this.survey.getQuestionStrings();
        this.questionTypes = this.survey.getQuestionTypes();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclerview_survey, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.question.setText(questionStrings.get(i));
        holder.questionType.setText(questionTypes.get(i));
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
            question = itemView.findViewById(R.id.textViewQuestionS);
            questionType = itemView.findViewById(R.id.textViewQuestionTypeS);
            parentLayout = itemView.findViewById(R.id.parent_layout_survey);

        }
    }
}
