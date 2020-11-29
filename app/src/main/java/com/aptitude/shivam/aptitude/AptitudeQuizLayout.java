package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aptitude.shivam.aptitude.Model.StatusModel;
import com.aptitude.shivam.aptitude.Model.UserModel;
import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Constants;
import com.aptitude.shivam.aptitude.Utils.Helper;

import java.util.HashMap;
import java.util.Map;

import static android.util.Log.d;

public class AptitudeQuizLayout extends AppCompatActivity implements View.OnClickListener {

    int result, index = 0;
    int TOTAL_QUESTIONS = Constants.allQuestionsAndOptions.size();
    public boolean isTestComplete = false;

    TextView question, option1, option2, option3, option4;
    public TextView timer;
    CardView submit;
    ImageView prevButton, nextButton;
    Map<Integer, String> map = new HashMap<>();
    Dialog loadingResultDialog;
    NetworkClient.ServerCommunicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aptitude_quiz_layout);

        init();

        loadingResultDialog = Helper.createDialog(this, R.layout.loading_dialog, "Generating your result");

        ThreadClass threadClass = new ThreadClass(); //User defined class
        Thread thread = new Thread(threadClass); //In-built class
        thread.start();
    }

    public void init() {
        question = findViewById(R.id.quizQuestion);
        timer = findViewById(R.id.timer);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        submit = findViewById(R.id.submit);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);

        question.setText((index + 1) + ". " + Constants.allQuestionsAndOptions.get(0).getQuestion());
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
        switch (v.getId()) {
            case R.id.option1:
                option1selected();
                map.put(index, "A");

                if (map.size() == TOTAL_QUESTIONS)
                    submit.setCardBackgroundColor(Color.rgb(0, 191, 255));
                break;

            case R.id.option2:
                option2selected();
                map.put(index, "B");

                if (map.size() == TOTAL_QUESTIONS)
                    submit.setCardBackgroundColor(Color.rgb(0, 191, 255));
                break;

            case R.id.option3:
                option3selected();
                map.put(index, "C");

                if (map.size() == TOTAL_QUESTIONS)
                    submit.setCardBackgroundColor(Color.rgb(0, 191, 255));
                break;

            case R.id.option4:
                option4selected();
                map.put(index, "D");

                if (map.size() == TOTAL_QUESTIONS)
                    submit.setCardBackgroundColor(Color.rgb(0, 191, 255));
                break;


            case R.id.nextButton:
                index = (index + 1) % TOTAL_QUESTIONS;
                question.setText((index + 1) + ". " + Constants.allQuestionsAndOptions.get(index).getQuestion());
                option1.setText(Constants.allQuestionsAndOptions.get(index).getOptionA());
                option2.setText(Constants.allQuestionsAndOptions.get(index).getOptionB());
                option3.setText(Constants.allQuestionsAndOptions.get(index).getOptionC());
                option4.setText(Constants.allQuestionsAndOptions.get(index).getOptionD());

                if (map.containsKey(index)) {
                    String option = map.get(index);
                    switch (option) {
                        case "A":
                            option1selected();
                            break;

                        case "B":
                            option2selected();
                            break;

                        case "C":
                            option3selected();
                            break;

                        case "D":
                            option4selected();
                            break;
                    }
                } else {
                    noOptionSelected();
                }
                break;


            case R.id.prevButton:
                if (index == 0)
                    index = TOTAL_QUESTIONS - 1;
                else
                    index -= 1;
                question.setText((index + 1) + ". " + Constants.allQuestionsAndOptions.get(index).getQuestion());
                option1.setText(Constants.allQuestionsAndOptions.get(index).getOptionA());
                option2.setText(Constants.allQuestionsAndOptions.get(index).getOptionB());
                option3.setText(Constants.allQuestionsAndOptions.get(index).getOptionC());
                option4.setText(Constants.allQuestionsAndOptions.get(index).getOptionD());

                if (map.containsKey(index)) {
                    String option = map.get(index);
                    switch (option) {
                        case "A":
                            option1selected();
                            break;

                        case "B":
                            option2selected();
                            break;

                        case "C":
                            option3selected();
                            break;

                        case "D":
                            option4selected();
                            break;
                    }
                } else {
                    noOptionSelected();
                }
                break;


            case R.id.submit:
                if (!(map.size() == TOTAL_QUESTIONS)) {
                    Toast.makeText(this, "Attempt all questions first!!", Toast.LENGTH_LONG).show();
                } else {
                    loadingResultDialog.show();

                    result = Helper.calcAptitudeScore(map);

                    //Store result to DB
                    communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);
                    UserModel userModel = new UserModel();
                    userModel.setUsername(Constants.Username);

                    if (Constants.test_type.equals("NA")) {
                        userModel.setNumerical(result);
                        Call<StatusModel> call = communicator.storeNAresult(userModel);
                        call.enqueue(new StoreNAresultHandler());

                    } else if (Constants.test_type.equals("PA")) {
                        userModel.setPerceptual(result);
                        Call<StatusModel> call2 = communicator.storePAresult(userModel);
                        call2.enqueue(new StorePAresultHandler());

                    } else {
                        userModel.setVerbal(result);
                        Call<StatusModel> call3 = communicator.storeVRresult(userModel);
                        call3.enqueue(new StoreVRresultHandler());
                    }
                }
                break;
        }
    }


    private class StoreNAresultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();
            if (statusModel.getStatus().equals("Success")) {
                Intent intent = new Intent(AptitudeQuizLayout.this, AptitudeResult.class);
                intent.putExtra("result", result);
                intent.putExtra("testType", "Numerical Aptitude");
                intent.putExtra("description", Constants.NA_description);
                startActivity(intent);
            } else {
                Toast.makeText(AptitudeQuizLayout.this, "Error!!", Toast.LENGTH_LONG).show();
            }
            finish();
            loadingResultDialog.dismiss();
        }

        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            d("TAG", "Error :" + t.getMessage());
            loadingResultDialog.dismiss();
            finish();
            Toast.makeText(AptitudeQuizLayout.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class StorePAresultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();
            if (statusModel.getStatus().equals("Success")) {
                Intent intent = new Intent(AptitudeQuizLayout.this, AptitudeResult.class);
                intent.putExtra("result", result);
                intent.putExtra("testType", "Perceptual Aptitude");
                intent.putExtra("description", Constants.PA_description);
                startActivity(intent);
            } else {
                Toast.makeText(AptitudeQuizLayout.this, "Error!!", Toast.LENGTH_LONG).show();
            }
            finish();
            loadingResultDialog.dismiss();
        }

        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            d("TAG", "Error :" + t.getMessage());
            loadingResultDialog.dismiss();
            finish();
            Toast.makeText(AptitudeQuizLayout.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class StoreVRresultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();
            if (statusModel.getStatus().equals("Success")) {
                Intent intent = new Intent(AptitudeQuizLayout.this, AptitudeResult.class);
                intent.putExtra("result", result);
                intent.putExtra("testType", "Verbal Reasoning");
                intent.putExtra("description", Constants.VR_description);
                startActivity(intent);
            } else {
                Toast.makeText(AptitudeQuizLayout.this, "Error!!", Toast.LENGTH_LONG).show();
            }
            finish();
            loadingResultDialog.dismiss();
        }

        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            d("TAG", "Error :" + t.getMessage());
            loadingResultDialog.dismiss();
            finish();
            Toast.makeText(AptitudeQuizLayout.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }


    private class ThreadClass implements Runnable {
        @Override
        public void run() {
            try {
                Log.d("TAG", "Started thread");
                int outerLoop = 9;
                int innerLoop = 59;

                while (outerLoop >= 0 && !isTestComplete) {
                    innerLoop = 59;
                    while (innerLoop >= 0 && !isTestComplete) {
                        Thread.sleep(1000);
                        //Log.d("TAG",outerLoop+":"+innerLoop);
                        setTextToTimer(outerLoop, innerLoop);
                        innerLoop -= 1;
                    }
                    outerLoop -= 1;
                }

                //After 10 mins are expired
                if (!isTestComplete) {
                    setTextToTimer(00, 00);

                    //Give zero score for all not attempted questions
                    for (int i = 0; i < TOTAL_QUESTIONS; i++) {
                        if (!map.containsKey(i)) {
                            map.put(i, "");
                        }
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(AptitudeQuizLayout.this, "Timer Expired !!", Toast.LENGTH_LONG).show();
                            loadingResultDialog.show();

                            result = Helper.calcAptitudeScore(map);

                            //Store result to DB
                            communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);
                            UserModel userModel = new UserModel();
                            userModel.setUsername(Constants.Username);

                            if (Constants.test_type.equals("NA")) {
                                userModel.setNumerical(result);
                                Call<StatusModel> call = communicator.storeNAresult(userModel);
                                call.enqueue(new StoreNAresultHandler());

                            } else if (Constants.test_type.equals("PA")) {
                                userModel.setPerceptual(result);
                                Call<StatusModel> call2 = communicator.storePAresult(userModel);
                                call2.enqueue(new StorePAresultHandler());

                            } else {
                                userModel.setVerbal(result);
                                Call<StatusModel> call3 = communicator.storeVRresult(userModel);
                                call3.enqueue(new StoreVRresultHandler());
                            }
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void setTextToTimer(final int outerloop, final int innerloop) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    timer.setText(outerloop + ":" + innerloop);
                }
            });
        }
    }

    public void option1selected() {
        option2.setBackgroundColor(getResources().getColor(R.color.white));
        option3.setBackgroundColor(getResources().getColor(R.color.white));
        option4.setBackgroundColor(getResources().getColor(R.color.white));
        option1.setBackgroundColor(getResources().getColor(R.color.blue));
        option2.setTextColor(getResources().getColor(R.color.darkBlue));
        option3.setTextColor(getResources().getColor(R.color.darkBlue));
        option4.setTextColor(getResources().getColor(R.color.darkBlue));
        option1.setTextColor(getResources().getColor(R.color.white));
    }

    public void option2selected() {
        option1.setBackgroundColor(getResources().getColor(R.color.white));
        option3.setBackgroundColor(getResources().getColor(R.color.white));
        option4.setBackgroundColor(getResources().getColor(R.color.white));
        option2.setBackgroundColor(getResources().getColor(R.color.blue));
        option1.setTextColor(getResources().getColor(R.color.darkBlue));
        option3.setTextColor(getResources().getColor(R.color.darkBlue));
        option4.setTextColor(getResources().getColor(R.color.darkBlue));
        option2.setTextColor(getResources().getColor(R.color.white));
    }

    public void option3selected() {
        option1.setBackgroundColor(getResources().getColor(R.color.white));
        option2.setBackgroundColor(getResources().getColor(R.color.white));
        option4.setBackgroundColor(getResources().getColor(R.color.white));
        option3.setBackgroundColor(getResources().getColor(R.color.blue));
        option1.setTextColor(getResources().getColor(R.color.darkBlue));
        option2.setTextColor(getResources().getColor(R.color.darkBlue));
        option4.setTextColor(getResources().getColor(R.color.darkBlue));
        option3.setTextColor(getResources().getColor(R.color.white));
    }

    public void option4selected() {
        option1.setBackgroundColor(getResources().getColor(R.color.white));
        option2.setBackgroundColor(getResources().getColor(R.color.white));
        option3.setBackgroundColor(getResources().getColor(R.color.white));
        option4.setBackgroundColor(getResources().getColor(R.color.blue));
        option1.setTextColor(getResources().getColor(R.color.darkBlue));
        option2.setTextColor(getResources().getColor(R.color.darkBlue));
        option3.setTextColor(getResources().getColor(R.color.darkBlue));
        option4.setTextColor(getResources().getColor(R.color.white));
    }

    public void noOptionSelected() {
        option1.setBackgroundColor(getResources().getColor(R.color.white));
        option2.setBackgroundColor(getResources().getColor(R.color.white));
        option3.setBackgroundColor(getResources().getColor(R.color.white));
        option4.setBackgroundColor(getResources().getColor(R.color.white));
        option1.setTextColor(getResources().getColor(R.color.darkBlue));
        option2.setTextColor(getResources().getColor(R.color.darkBlue));
        option3.setTextColor(getResources().getColor(R.color.darkBlue));
        option4.setTextColor(getResources().getColor(R.color.darkBlue));
    }

}
