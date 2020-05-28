package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aptitude.shivam.aptitude.Model.OceanQuestionModel;
import com.aptitude.shivam.aptitude.Model.QuizModel;
import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Constants;
import com.aptitude.shivam.aptitude.Utils.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.util.Log.d;


public class Quiz_layout extends AppCompatActivity implements View.OnClickListener {

    int index = 0;
    int TOTAL_QUESTIONS = 5;

    TextView questionView;
    RadioButton option1, option2, option3, option4, option5;
    RadioGroup rg;
    ImageView nextButton, prevButton;
    CardView submitButton;
    QuizModel quizModel = new QuizModel();
    Map<Integer, Integer> map = new HashMap<>();
    List<String> questionsList = new ArrayList<>();

    NetworkClient.ServerCommunicator communicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_layout);

        Log.d("TAG","In quiz layout");
        communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);
        Call<OceanQuestionModel> call = communicator.getOceanQuestions();
        call.enqueue(new QuestionGetterHandler());

        init();
    }

    public void init() {
        questionView = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        option5 = findViewById(R.id.option5);
        rg = findViewById(R.id.RadioGroup);
        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);
        submitButton = findViewById(R.id.submit);

//        questionView.setText(index + 1 + ". " + quizModel.questionsList.get(index));
        option1.setText(quizModel.option1);
        option2.setText(quizModel.option2);
        option3.setText(quizModel.option3);
        option4.setText(quizModel.option4);
        option5.setText(quizModel.option5);

        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        int weight = 0;
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.option1:   //Disagree
                weight = 1;
                break;

            case R.id.option2:   //Slightly disagree
                weight = 2;
                break;

            case R.id.option3:   //Neutral
                weight = 3;
                break;

            case R.id.option4:   //Slightly agree
                weight = 4;
                break;

            case R.id.option5:   //Agree
                weight = 5;
                break;
        }

        if (checked) {
            map.put(index, weight);
            if (map.size() == TOTAL_QUESTIONS)
                submitButton.setCardBackgroundColor(Color.rgb(0, 191, 255));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextButton:
                index = (index + 1) % quizModel.questionsList.size();
                d("TAG", "Total size = " + quizModel.questionsList.size() + " index = " + index);

                questionView.setText(index + 1 + ". " + quizModel.questionsList.get(index));

                if (map.containsKey(index)) {
                    int weight = map.get(index);
                    switch (weight) {
                        case 1:
                            rg.check(R.id.option1);
                            break;
                        case 2:
                            rg.check(R.id.option2);
                            break;
                        case 3:
                            rg.check(R.id.option3);
                            break;
                        case 4:
                            rg.check(R.id.option4);
                            break;
                        case 5:
                            rg.check(R.id.option5);
                            break;
                    }
                } else
                    rg.clearCheck();

                break;


            case R.id.prevButton:
                if(index == 0)
                    index = quizModel.questionsList.size()-1;
                else
                    index -=1;
                questionView.setText((index+1)+ ". " + quizModel.questionsList.get(index));

                if (map.containsKey(index)) {
                    int weight = map.get(index);
                    switch (weight) {
                        case 1:
                            rg.check(R.id.option1);
                            break;
                        case 2:
                            rg.check(R.id.option2);
                            break;
                        case 3:
                            rg.check(R.id.option3);
                            break;
                        case 4:
                            rg.check(R.id.option4);
                            break;
                        case 5:
                            rg.check(R.id.option5);
                            break;
                    }
                } else
                    rg.clearCheck();

                break;


            case R.id.submit:
                //First caluculate result
                ArrayList<Integer> sortedResult = new ArrayList<>();
                ArrayList<Integer> result = new ArrayList<>();
                //Sort the hashmap according to keys(i.e index) and store only values in list
                for(int i=0;i<5;i++){
                    sortedResult.add(map.get(i));
                }
                quizModel.populateAnswersList(sortedResult);
                //result = Helper.calcPersonality(sortedResult);
                result.add(45);
                result.add(50);
                result.add(60);
                result.add(70);
                result.add(80);
                Intent intent = new Intent(Quiz_layout.this, OceanResult.class);
                intent.putIntegerArrayListExtra("result",result);
                startActivity(intent);
                //Log.d("sorted result",sortedResult.toString());
                //Then push to backend
                break;
        }
    }

    private class QuestionGetterHandler implements Callback<OceanQuestionModel> {
        @Override
        public void onResponse(Call<OceanQuestionModel> call, Response<OceanQuestionModel> response) {
            Log.d("TAG","Response = "+response.body());
        }

        @Override
        public void onFailure(Call<OceanQuestionModel> call, Throwable t) {
            Log.d("TAG","Fail = "+t.getMessage());
        }
    }

}
