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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aptitude.shivam.aptitude.Model.StatusModel;
import com.aptitude.shivam.aptitude.Model.UserModel;
import com.aptitude.shivam.aptitude.Model.UserModelGrad;
import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Constants;
import com.aptitude.shivam.aptitude.Utils.Helper;

import java.util.HashMap;
import java.util.Map;

import static android.util.Log.d;

public class GradTestLayout extends AppCompatActivity implements View.OnClickListener {

    int result, index = 0;
    int TOTAL_QUESTIONS = Constants.allQuestionsAndOptions.size();

    TextView question, option1, option2, option3, option4;
    CardView submit;
    ImageView prevButton, nextButton;
    Map<Integer, String> map = new HashMap<>();
    Dialog loadingResultDialog;
    NetworkClient.ServerCommunicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grad_test_layout);

        init();

        loadingResultDialog = Helper.createDialog(this, R.layout.loading_dialog, "Generating your result");
    }

    public void init(){
        question = findViewById(R.id.quizQuestion);
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
                    UserModelGrad userModel = new UserModelGrad();  //Note I've used UserModelGrad and not UserModel
                    userModel.setUsername(Constants.Username);

                    if (Constants.test_type.equals("Medical")) {
                        userModel.setMedical(result);
                        Call<StatusModel> call = communicator.storeMedicalResult(userModel);
                        call.enqueue(new StoreMedicalResultHandler());

                    } else if (Constants.test_type.equals("Political")) {
                        userModel.setPolitical(result);
                        Call<StatusModel> call2 = communicator.storePoliticalResult(userModel);
                        call2.enqueue(new StorePoliticalResultHandler());

                    } else if (Constants.test_type.equals("Management")) {
                        userModel.setManagement(result);
                        Call<StatusModel> call3 = communicator.storeManagementResult(userModel);
                        call3.enqueue(new StoreManagementResultHandler());

                    } else if (Constants.test_type.equals("Computer")) {
                        userModel.setComputer(result);
                        Call<StatusModel> call4 = communicator.storeComputerResult(userModel);
                        call4.enqueue(new StoreComputerResultHandler());

                    } else if (Constants.test_type.equals("Mechanical")) {
                        userModel.setMechanical(result);
                        Call<StatusModel> call5 = communicator.storeMechanicalResult(userModel);
                        call5.enqueue(new StoreMechanicalResultHandler());

                    } else if (Constants.test_type.equals("Aerospace")){
                        userModel.setAerospace(result);
                        Call<StatusModel> call6 = communicator.storeAerospaceResult(userModel);
                        call6.enqueue(new StoreAerospaceResultHandler());
                    }
                }
                break;
        }
    }

    private class StoreMedicalResultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();
            if (statusModel.getStatus().equals("Success")) {
                Intent intent = new Intent(GradTestLayout.this, AptitudeResult.class);
                intent.putExtra("result", result);
                intent.putExtra("testType", "Medical Aptitude");
                intent.putExtra("description", "");
                startActivity(intent);
            } else {
                Toast.makeText(GradTestLayout.this, "Error!!", Toast.LENGTH_LONG).show();
            }
            finish();
            loadingResultDialog.dismiss();
        }
        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            d("TAG", "Error :" + t.getMessage());
            loadingResultDialog.dismiss();
            finish();
            Toast.makeText(GradTestLayout.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class StorePoliticalResultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();
            if (statusModel.getStatus().equals("Success")) {
                Intent intent = new Intent(GradTestLayout.this, AptitudeResult.class);
                intent.putExtra("result", result);
                intent.putExtra("testType", "Public & Political Affairs Aptitude");
                intent.putExtra("description", "");
                startActivity(intent);
            } else {
                Toast.makeText(GradTestLayout.this, "Error!!", Toast.LENGTH_LONG).show();
            }
            finish();
            loadingResultDialog.dismiss();
        }
        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            d("TAG", "Error :" + t.getMessage());
            loadingResultDialog.dismiss();
            finish();
            Toast.makeText(GradTestLayout.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class StoreManagementResultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();
            if (statusModel.getStatus().equals("Success")) {
                Intent intent = new Intent(GradTestLayout.this, AptitudeResult.class);
                intent.putExtra("result", result);
                intent.putExtra("testType", "Management Studies Aptitude");
                intent.putExtra("description", "");
                startActivity(intent);
            } else {
                Toast.makeText(GradTestLayout.this, "Error!!", Toast.LENGTH_LONG).show();
            }
            finish();
            loadingResultDialog.dismiss();
        }
        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            d("TAG", "Error :" + t.getMessage());
            loadingResultDialog.dismiss();
            finish();
            Toast.makeText(GradTestLayout.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class StoreComputerResultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();
            if (statusModel.getStatus().equals("Success")) {
                Intent intent = new Intent(GradTestLayout.this, AptitudeResult.class);
                intent.putExtra("result", result);
                intent.putExtra("testType", "CSE/IT Aptitude");
                intent.putExtra("description", "");
                startActivity(intent);
            } else {
                Toast.makeText(GradTestLayout.this, "Error!!", Toast.LENGTH_LONG).show();
            }
            finish();
            loadingResultDialog.dismiss();
        }
        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            d("TAG", "Error :" + t.getMessage());
            loadingResultDialog.dismiss();
            finish();
            Toast.makeText(GradTestLayout.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class StoreMechanicalResultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();
            if (statusModel.getStatus().equals("Success")) {
                Intent intent = new Intent(GradTestLayout.this, AptitudeResult.class);
                intent.putExtra("result", result);
                intent.putExtra("testType", "Mechanical Engineering Aptitude");
                intent.putExtra("description", "");
                startActivity(intent);
            } else {
                Toast.makeText(GradTestLayout.this, "Error!!", Toast.LENGTH_LONG).show();
            }
            finish();
            loadingResultDialog.dismiss();
        }
        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            d("TAG", "Error :" + t.getMessage());
            loadingResultDialog.dismiss();
            finish();
            Toast.makeText(GradTestLayout.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class StoreAerospaceResultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();
            if (statusModel.getStatus().equals("Success")) {
                Intent intent = new Intent(GradTestLayout.this, AptitudeResult.class);
                intent.putExtra("result", result);
                intent.putExtra("testType", "Aerospace Engineering Aptitude");
                intent.putExtra("description", "");
                startActivity(intent);
            } else {
                Toast.makeText(GradTestLayout.this, "Error!!", Toast.LENGTH_LONG).show();
            }
            finish();
            loadingResultDialog.dismiss();
        }
        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            d("TAG", "Error :" + t.getMessage());
            loadingResultDialog.dismiss();
            finish();
            Toast.makeText(GradTestLayout.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
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