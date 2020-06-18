package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aptitude.shivam.aptitude.Utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class AptitudeQuizLayout extends AppCompatActivity implements View.OnClickListener {

    int index = 0;
    int TOTAL_QUESTIONS = Constants.allQuestionsAndOptions.size();
    public boolean isTestComplete = false;

    TextView question, option1, option2, option3, option4;
    public TextView timer;
    CardView submit;
    ImageView prevButton, nextButton;
    Map<Integer, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aptitude_quiz_layout);

        ThreadClass threadClass = new ThreadClass(); //User defined class
        Thread thread = new Thread(threadClass); //In-built class
        thread.start();

        init();
    }

    public void init(){
        question = findViewById(R.id.quizQuestion);
        timer = findViewById(R.id.timer);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        submit = findViewById(R.id.submit);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);

        question.setText(Constants.allQuestionsAndOptions.get(0).getQuestion());
        option1.setText(Constants.allQuestionsAndOptions.get(0).getOptionA());
        option2.setText(Constants.allQuestionsAndOptions.get(0).getOptionB());
        option3.setText(Constants.allQuestionsAndOptions.get(0).getOptionC());
        option4.setText(Constants.allQuestionsAndOptions.get(0).getOptionD());

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.option1:
                option2.setBackgroundColor(getResources().getColor(R.color.white));
                option3.setBackgroundColor(getResources().getColor(R.color.white));
                option4.setBackgroundColor(getResources().getColor(R.color.white));
                option1.setBackgroundColor(getResources().getColor(R.color.newblue));
                map.put(index,"A");

                if (map.size() == TOTAL_QUESTIONS)
                    submit.setCardBackgroundColor(Color.rgb(0, 191, 255));
                break;

            case R.id.option2:
                option1.setBackgroundColor(getResources().getColor(R.color.white));
                option3.setBackgroundColor(getResources().getColor(R.color.white));
                option4.setBackgroundColor(getResources().getColor(R.color.white));
                option2.setBackgroundColor(getResources().getColor(R.color.newblue));
                map.put(index,"B");

                if (map.size() == TOTAL_QUESTIONS)
                    submit.setCardBackgroundColor(Color.rgb(0, 191, 255));
                break;

            case R.id.option3:
                option1.setBackgroundColor(getResources().getColor(R.color.white));
                option2.setBackgroundColor(getResources().getColor(R.color.white));
                option4.setBackgroundColor(getResources().getColor(R.color.white));
                option3.setBackgroundColor(getResources().getColor(R.color.newblue));
                map.put(index,"C");

                if (map.size() == TOTAL_QUESTIONS)
                    submit.setCardBackgroundColor(Color.rgb(0, 191, 255));
                break;

            case R.id.option4:
                option1.setBackgroundColor(getResources().getColor(R.color.white));
                option2.setBackgroundColor(getResources().getColor(R.color.white));
                option3.setBackgroundColor(getResources().getColor(R.color.white));
                option4.setBackgroundColor(getResources().getColor(R.color.newblue));
                map.put(index,"D");

                if (map.size() == TOTAL_QUESTIONS)
                    submit.setCardBackgroundColor(Color.rgb(0, 191, 255));
                break;


            case R.id.nextButton:
                index = (index + 1) % TOTAL_QUESTIONS;
                question.setText(Constants.allQuestionsAndOptions.get(index).getQuestion());
                option1.setText(Constants.allQuestionsAndOptions.get(index).getOptionA());
                option2.setText(Constants.allQuestionsAndOptions.get(index).getOptionB());
                option3.setText(Constants.allQuestionsAndOptions.get(index).getOptionC());
                option4.setText(Constants.allQuestionsAndOptions.get(index).getOptionD());

                if (map.containsKey(index)) {
                    String option = map.get(index);
                    switch (option){
                        case "A":
                            option2.setBackgroundColor(getResources().getColor(R.color.white));
                            option3.setBackgroundColor(getResources().getColor(R.color.white));
                            option4.setBackgroundColor(getResources().getColor(R.color.white));
                            option1.setBackgroundColor(getResources().getColor(R.color.newblue));
                            break;

                        case "B":
                            option1.setBackgroundColor(getResources().getColor(R.color.white));
                            option3.setBackgroundColor(getResources().getColor(R.color.white));
                            option4.setBackgroundColor(getResources().getColor(R.color.white));
                            option2.setBackgroundColor(getResources().getColor(R.color.newblue));
                            break;

                        case "C":
                            option1.setBackgroundColor(getResources().getColor(R.color.white));
                            option2.setBackgroundColor(getResources().getColor(R.color.white));
                            option4.setBackgroundColor(getResources().getColor(R.color.white));
                            option3.setBackgroundColor(getResources().getColor(R.color.newblue));
                            break;

                        case "D":
                            option1.setBackgroundColor(getResources().getColor(R.color.white));
                            option2.setBackgroundColor(getResources().getColor(R.color.white));
                            option3.setBackgroundColor(getResources().getColor(R.color.white));
                            option4.setBackgroundColor(getResources().getColor(R.color.newblue));
                            break;
                    }
                } else{
                    option1.setBackgroundColor(getResources().getColor(R.color.white));
                    option2.setBackgroundColor(getResources().getColor(R.color.white));
                    option3.setBackgroundColor(getResources().getColor(R.color.white));
                    option4.setBackgroundColor(getResources().getColor(R.color.white));
                }
                break;


            case R.id.prevButton:
                if(index == 0)
                    index = TOTAL_QUESTIONS-1;
                else
                    index -=1;
                question.setText(Constants.allQuestionsAndOptions.get(index).getQuestion());
                option1.setText(Constants.allQuestionsAndOptions.get(index).getOptionA());
                option2.setText(Constants.allQuestionsAndOptions.get(index).getOptionB());
                option3.setText(Constants.allQuestionsAndOptions.get(index).getOptionC());
                option4.setText(Constants.allQuestionsAndOptions.get(index).getOptionD());

                if (map.containsKey(index)) {
                    String option = map.get(index);
                    switch (option){
                        case "A":
                            option2.setBackgroundColor(getResources().getColor(R.color.white));
                            option3.setBackgroundColor(getResources().getColor(R.color.white));
                            option4.setBackgroundColor(getResources().getColor(R.color.white));
                            option1.setBackgroundColor(getResources().getColor(R.color.newblue));
                            break;

                        case "B":
                            option1.setBackgroundColor(getResources().getColor(R.color.white));
                            option3.setBackgroundColor(getResources().getColor(R.color.white));
                            option4.setBackgroundColor(getResources().getColor(R.color.white));
                            option2.setBackgroundColor(getResources().getColor(R.color.newblue));
                            break;

                        case "C":
                            option1.setBackgroundColor(getResources().getColor(R.color.white));
                            option2.setBackgroundColor(getResources().getColor(R.color.white));
                            option4.setBackgroundColor(getResources().getColor(R.color.white));
                            option3.setBackgroundColor(getResources().getColor(R.color.newblue));
                            break;

                        case "D":
                            option1.setBackgroundColor(getResources().getColor(R.color.white));
                            option2.setBackgroundColor(getResources().getColor(R.color.white));
                            option3.setBackgroundColor(getResources().getColor(R.color.white));
                            option4.setBackgroundColor(getResources().getColor(R.color.newblue));
                            break;
                    }
                } else{
                    option1.setBackgroundColor(getResources().getColor(R.color.white));
                    option2.setBackgroundColor(getResources().getColor(R.color.white));
                    option3.setBackgroundColor(getResources().getColor(R.color.white));
                    option4.setBackgroundColor(getResources().getColor(R.color.white));
                }
                break;


            case R.id.submit:
                break;
        }
    }

    private class ThreadClass implements Runnable{
        @Override
        public void run() {
            try{
                Log.d("TAG","Started thread");
                int outerLoop = 9;
                int innerLoop = 59;

                while(outerLoop>=0 && !isTestComplete){
                    innerLoop = 59;
                    while(innerLoop>=0 && !isTestComplete){
                        Thread.sleep(1000);
                        //timer.setText(outerLoop+":"+innerLoop);
                        Log.d("TAG",outerLoop+":"+innerLoop);
                        setTextToTimer(outerLoop,innerLoop);
                        innerLoop -= 1;
                    }
                    outerLoop -= 1;
                }

                //After 10 mins
                if(!isTestComplete){
                    //timer.setText("00:00");
                    Log.d("TAG",outerLoop+":"+innerLoop);
                    //start activity
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        public void setTextToTimer(final int outerloop,final int innerloop){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    timer.setText(outerloop+" : "+innerloop);
                }
            });

        }

    }

}
