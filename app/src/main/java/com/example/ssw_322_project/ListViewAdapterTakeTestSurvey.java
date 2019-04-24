package com.example.ssw_322_project;

import android.content.Context;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ssw_322_project.ClassesAndInterfaces.MultipleChoiceQuestion;
import com.example.ssw_322_project.ClassesAndInterfaces.Question;
import com.example.ssw_322_project.ClassesAndInterfaces.ShortAnswerQuestion;
import com.example.ssw_322_project.ClassesAndInterfaces.Survey;
import com.example.ssw_322_project.ClassesAndInterfaces.TrueFalseQuestion;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;



public class ListViewAdapterTakeTestSurvey extends ArrayAdapter<Question> {

    private static final String TAG = "AdapterSurveyTestTaking";

    private ArrayList<Question> questionArrayList = new ArrayList<Question>();

    public ListViewAdapterTakeTestSurvey(Context mContext, ArrayList<Question> questionArrayList){
        super(mContext, 0, questionArrayList);
        this.questionArrayList = questionArrayList;
    }


    public View inflateView(int position, ViewGroup container){
        String questionType = getItem(position).getQuestionType();

        if (questionType.equals("Multiple Choice")){
            return LayoutInflater.from(getContext()).inflate(R.layout.take_multiple_choice, null);
        } else if (questionType.equals("Short Answer")){
            return LayoutInflater.from(getContext()).inflate(R.layout.take_short_answer, null);
        } else  { //True false
            return LayoutInflater.from(getContext()).inflate(R.layout.take_true_false, null);
        }
    }

    public View populateView(int position, View convertView, ViewGroup container){
        Question question = getItem(position);
        String questionType = getItem(position).getQuestionType();

        if (questionType.equals("Multiple Choice")){
            return populateMCQView(convertView, container, question);
        } else if (questionType.equals("Short Answer")){
            return populateSAQView(convertView, container, question);
        } else  { //True false
            return populateTFQView(convertView, container, question);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container){
        if (convertView == null) {
            convertView = inflateView(position, container);
            convertView = populateView(position, convertView, container);
        }

        return convertView;

    }


    private View populateMCQView(View view, ViewGroup viewGroup, Question mcq){

        mcq = (MultipleChoiceQuestion) mcq;
        TextView questionPrompt = (TextView) view.findViewById(R.id.takeMCQTextView);
        questionPrompt.setText(mcq.getQuestion());

        RadioGroup mcRadioGroup = (RadioGroup) view.findViewById(R.id.takeMCQRadioGroup);
        RadioButton option1 = (RadioButton) view.findViewById(R.id.takeMCQRadioChoice1);
        RadioButton option2 = (RadioButton) view.findViewById(R.id.takeMCQRadioChoice2);
        RadioButton option3 = (RadioButton) view.findViewById(R.id.takeMCQRadioChoice3);
        RadioButton option4 = (RadioButton) view.findViewById(R.id.takeMCQRadioChoice4);

        option1.setText(((MultipleChoiceQuestion) mcq).getOption1());
        option2.setText(((MultipleChoiceQuestion) mcq).getOption2());
        option3.setText(((MultipleChoiceQuestion) mcq).getOption3());
        option4.setText(((MultipleChoiceQuestion) mcq).getOption4());

        return view;
    }

    private View populateSAQView(View view, ViewGroup viewGroup, Question saq){

        saq = (ShortAnswerQuestion) saq;
        TextView questionPrompt = (TextView) view.findViewById(R.id.takeSAQTextView);
        questionPrompt.setText(saq.getQuestion());

        MaterialEditText answerField = (MaterialEditText) view.findViewById(R.id.takeSAQAnswerField);

        return view;
    }

    private View populateTFQView(View view, ViewGroup viewGroup, Question tfq){

        tfq = (TrueFalseQuestion) tfq;
        TextView questionPrompt = (TextView) view.findViewById(R.id.takeTFQTextView);
        questionPrompt.setText(tfq.getQuestion());

        RadioGroup tfRadioGroup = (RadioGroup) view.findViewById(R.id.takeTFQRadioGroup);
        RadioButton trueRadio = (RadioButton) view.findViewById(R.id.tfTakeTrue);
        RadioButton falseRadio = (RadioButton) view.findViewById(R.id.tfTakeFalse);

        return view;
    }

}
