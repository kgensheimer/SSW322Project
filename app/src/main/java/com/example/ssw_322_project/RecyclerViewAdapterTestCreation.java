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

import com.example.ssw_322_project.ClassesAndInterfaces.Question;
import com.example.ssw_322_project.ClassesAndInterfaces.Test;

import java.util.ArrayList;

public class RecyclerViewAdapterTestCreation extends RecyclerView.Adapter<RecyclerViewAdapterTestCreation.ViewHolder> {

    private static final String TAG = "AdapterTestCreation";

    private Test test;
    private Context mContext;

    private ArrayList<String> questionStrings = new ArrayList<String>();
    private ArrayList<String> questionTypes = new ArrayList<String>();
    private ArrayList<String> answers = new ArrayList<String>();

    private int focusedItem;

    public RecyclerViewAdapterTestCreation(Context mContext, Test test) {
        this.test = test;
        this.mContext = mContext;
        this.questionStrings = this.test.getQuestionStrings();
        this.questionTypes = this.test.getQuestionTypes();
        this.answers = this.test.getAnswers();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclerview_test, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.question.setText(questionStrings.get(i));
        holder.questionType.setText(questionTypes.get(i));
        holder.answer.setText(answers.get(i));

        //unhighlights the previous item
        if (focusedItem == i) {
            holder.parentLayout.setSelected(true);
        } else {
            holder.parentLayout.setSelected(false);
        }


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + questionStrings.get(i));
                //Following assigns the clicked question to the focused question
                notifyItemChanged(i);
                focusedItem = i;
                holder.parentLayout.setSelected(true);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return questionStrings.size();
    }

    public int getFocusedItem(){
        return focusedItem;
    }

    public Question getFocusedQuestion(){
        if (test.getQuestionArrayList().size() == 0)
            return null;
        return test.getQuestionArrayList().get(focusedItem);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView question;
        TextView questionType;
        TextView answer;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.textViewQuestionT);
            questionType = itemView.findViewById(R.id.textViewQuestionTypeT);
            answer = itemView.findViewById(R.id.textViewAnswerT);
            parentLayout = itemView.findViewById(R.id.parent_layout_test);

        }
    }
}
