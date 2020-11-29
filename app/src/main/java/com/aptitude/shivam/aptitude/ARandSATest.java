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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aptitude.shivam.aptitude.Model.ImageModel;
import com.aptitude.shivam.aptitude.Model.StatusModel;
import com.aptitude.shivam.aptitude.Model.UserModel;
import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Constants;
import com.aptitude.shivam.aptitude.Utils.Helper;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.util.Log.d;

public class ARandSATest extends AppCompatActivity implements View.OnClickListener {

    int result, index = 0;
    int TOTAL_QUESTIONS = 30;
    String question, options;
    public boolean isTestComplete = false;

    public TextView timer;
    TextView question_text, question_index;
    RadioButton optionA, optionB, optionC, optionD;
    RadioGroup rg;
    ImageView nextButton, prevButton, question_img, options_img;
    CardView submitButton;
    Map<Integer, String> map = new HashMap<>();
    String[] spatialTextQuestions = new String[]{};
    List<ImageModel> imageModel;
    Dialog loadingResultDialog;
    NetworkClient.ServerCommunicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_and_sa_test);

        loadingResultDialog = Helper.createDialog(this, R.layout.loading_dialog, "Generating your result");

        init();

        if (Constants.test_type.equals("AR")) {
            imageModel = Helper.abstractModelList();
        } else {
            imageModel = Helper.spatialModelList();
        }

        if (Constants.test_type.equals("SA")) {
            question_text.setText(spatialTextQuestions[index]);
        }
        question_index.setText((index + 1) + ".");
        question = imageModel.get(index).getQuestion();
        options = imageModel.get(index).getOptions();
        Glide.with(this).load(getResources().getIdentifier(question, "drawable", this.getPackageName())).into(question_img);
        Glide.with(this).load(getResources().getIdentifier(options, "drawable", this.getPackageName())).into(options_img);

        ThreadClass threadClass = new ThreadClass(); //User defined class
        Thread thread = new Thread(threadClass); //In-built class
        thread.start();
    }

    public void init() {
        timer = findViewById(R.id.timer);
        question_text = findViewById(R.id.question_text);
        question_index = findViewById(R.id.index);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        rg = findViewById(R.id.RadioGroup);
        question_img = findViewById(R.id.question_img);
        options_img = findViewById(R.id.options_img);
        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);
        submitButton = findViewById(R.id.submit);

        spatialTextQuestions = Constants.spatialTextQuestions;

        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        String weight = "";
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.optionA:
                weight = "A";
                break;

            case R.id.optionB:
                weight = "B";
                break;

            case R.id.optionC:
                weight = "C";
                break;

            case R.id.optionD:
                weight = "D";
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
                index = (index + 1) % TOTAL_QUESTIONS;

                if (Constants.test_type.equals("SA")) {
                    question_text.setText(spatialTextQuestions[index]);
                }
                question_index.setText((index + 1) + ".");
                question = imageModel.get(index).getQuestion();
                options = imageModel.get(index).getOptions();
                Glide.with(this).load(getResources().getIdentifier(question, "drawable", this.getPackageName())).into(question_img);
                Glide.with(this).load(getResources().getIdentifier(options, "drawable", this.getPackageName())).into(options_img);

                if (map.containsKey(index)) {
                    String weight = map.get(index);

                    switch (weight) {
                        case "A":
                            rg.check(R.id.optionA);
                            break;
                        case "B":
                            rg.check(R.id.optionB);
                            break;
                        case "C":
                            rg.check(R.id.optionC);
                            break;
                        case "D":
                            rg.check(R.id.optionD);
                            break;
                    }
                } else
                    rg.clearCheck();
                break;


            case R.id.prevButton:
                if (index == 0)
                    index = TOTAL_QUESTIONS - 1;
                else
                    index -= 1;

                if (Constants.test_type.equals("SA")) {
                    question_text.setText(spatialTextQuestions[index]);
                }
                question_index.setText((index + 1) + ".");
                question = imageModel.get(index).getQuestion();
                options = imageModel.get(index).getOptions();
                Glide.with(this).load(getResources().getIdentifier(question, "drawable", this.getPackageName())).into(question_img);
                Glide.with(this).load(getResources().getIdentifier(options, "drawable", this.getPackageName())).into(options_img);

                if (map.containsKey(index)) {
                    String weight = map.get(index);

                    switch (weight) {
                        case "A":
                            rg.check(R.id.optionA);
                            break;
                        case "B":
                            rg.check(R.id.optionB);
                            break;
                        case "C":
                            rg.check(R.id.optionC);
                            break;
                        case "D":
                            rg.check(R.id.optionD);
                            break;
                    }
                } else
                    rg.clearCheck();
                break;


            case R.id.submit:
                if (!(map.size() == TOTAL_QUESTIONS)) {
                    Toast.makeText(this, "Attempt all questions first!!", Toast.LENGTH_LONG).show();
                } else {
                    loadingResultDialog.show();

                    result = Helper.calc_ARandSA_score(map, imageModel);

                    //Store result to DB
                    communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);
                    UserModel userModel = new UserModel();
                    userModel.setUsername(Constants.Username);

                    if (Constants.test_type.equals("AR")) {
                        userModel.setAbstractApti(result);
                        Call<StatusModel> call = communicator.storeARresult(userModel);
                        call.enqueue(new StoreARresultHandler());
                    } else {
                        userModel.setSpatial(result);
                        Call<StatusModel> call2 = communicator.storeSAresult(userModel);
                        call2.enqueue(new StoreSAresultHandler());
                    }
                }
                break;
        }
    }

    private class StoreARresultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();

            if (statusModel.getStatus().equals("Success")) {
                Intent intent = new Intent(ARandSATest.this, AptitudeResult.class);
                intent.putExtra("result", result);
                intent.putExtra("testType", "Abstract Reasoning");
                intent.putExtra("description", Constants.AR_description);
                startActivity(intent);
            } else {
                Toast.makeText(ARandSATest.this, "Error!!", Toast.LENGTH_LONG).show();
            }
            finish();
            loadingResultDialog.dismiss();
        }

        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            d("TAG", "Error :" + t.getMessage());
            loadingResultDialog.dismiss();
            Toast.makeText(ARandSATest.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class StoreSAresultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();

            if (statusModel.getStatus().equals("Success")) {
                Intent intent = new Intent(ARandSATest.this, AptitudeResult.class);
                intent.putExtra("result", result);
                intent.putExtra("testType", "Spatial Aptitude");
                intent.putExtra("description", Constants.SA_description);
                startActivity(intent);
            } else {
                Toast.makeText(ARandSATest.this, "Error!!", Toast.LENGTH_LONG).show();
            }
            finish();
            loadingResultDialog.dismiss();
        }

        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            d("TAG", "Error :" + t.getMessage());
            loadingResultDialog.dismiss();
            Toast.makeText(ARandSATest.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
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

                    for (int i = 0; i < TOTAL_QUESTIONS; i++) {
                        if (!map.containsKey(i)) {
                            map.put(i, "");
                        }
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ARandSATest.this, "Timer Expired !!", Toast.LENGTH_LONG).show();
                            loadingResultDialog.show();

                            result = Helper.calc_ARandSA_score(map, imageModel);

                            //Store result to DB
                            communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);
                            UserModel userModel = new UserModel();
                            userModel.setUsername(Constants.Username);

                            if (Constants.test_type.equals("AR")) {
                                userModel.setAbstractApti(result);
                                Call<StatusModel> call = communicator.storeARresult(userModel);
                                call.enqueue(new StoreARresultHandler());
                            } else {
                                userModel.setSpatial(result);
                                Call<StatusModel> call2 = communicator.storeSAresult(userModel);
                                call2.enqueue(new StoreSAresultHandler());
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
}