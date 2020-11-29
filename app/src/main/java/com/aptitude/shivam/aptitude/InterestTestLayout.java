package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aptitude.shivam.aptitude.Model.OceanModel;
import com.aptitude.shivam.aptitude.Model.OceanQuestionModel;
import com.aptitude.shivam.aptitude.Model.QuizModel;
import com.aptitude.shivam.aptitude.Model.RaisecModel;
import com.aptitude.shivam.aptitude.Model.RaisecQuestionModel;
import com.aptitude.shivam.aptitude.Model.StatusModel;
import com.aptitude.shivam.aptitude.Model.UserModel;
import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Constants;
import com.aptitude.shivam.aptitude.Utils.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.util.Log.d;

public class InterestTestLayout extends AppCompatActivity implements View.OnClickListener {

    int img_counter = 1;
    int index = 0;
    int TOTAL_QUESTIONS = 42;

    TextView questionView, option1, option2, option3, option4, option5;
    ImageView nextButton, prevButton, image;
    CardView submitButton;
    QuizModel quizModel = new QuizModel();
    Map<Integer, Integer> map = new HashMap<>();
    List<String> questionsList = new ArrayList<>();
    Dialog loadingQuestionDialog,loadingResultDialog;
    NetworkClient.ServerCommunicator communicator;
    ArrayList<Integer> result = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_test_layout);

        loadingQuestionDialog = Helper.createDialog(this, R.layout.loading_dialog, "Loading Questions");
        loadingResultDialog = Helper.createDialog(this, R.layout.loading_dialog, "Generating your result");

        init();
    }

    public void init() {
        questionView = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        option5 = findViewById(R.id.option5);
        image = findViewById(R.id.image);
        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);
        submitButton = findViewById(R.id.submit);

        option1.setText(quizModel.option1);
        option2.setText(quizModel.option2);
        option3.setText(quizModel.option3);
        option4.setText(quizModel.option4);
        option5.setText(quizModel.option5);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        option5.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        loadingQuestionDialog.show();
        communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);
        Call<List<RaisecQuestionModel>> call = communicator.getInterestQuestions();
        call.enqueue(new QuestionGetterHandler());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.option1:
                option1selected();
                map.put(index, 1);  //Disagree

                if (map.size() == TOTAL_QUESTIONS)
                    submitButton.setCardBackgroundColor(Color.rgb(0, 191, 255));
                break;

            case R.id.option2:
                option2selected();
                map.put(index, 2);  //Slightly Disagree

                if (map.size() == TOTAL_QUESTIONS)
                    submitButton.setCardBackgroundColor(Color.rgb(0, 191, 255));
                break;

            case R.id.option3:
                option3selected();
                map.put(index, 3);  //Neutral

                if (map.size() == TOTAL_QUESTIONS)
                    submitButton.setCardBackgroundColor(Color.rgb(0, 191, 255));
                break;

            case R.id.option4:
                option4selected();
                map.put(index, 4);  //Slightly Agree

                if (map.size() == TOTAL_QUESTIONS)
                    submitButton.setCardBackgroundColor(Color.rgb(0, 191, 255));
                break;

            case R.id.option5:
                option5selected();
                map.put(index, 5);  //Agree

                if (map.size() == TOTAL_QUESTIONS)
                    submitButton.setCardBackgroundColor(Color.rgb(0, 191, 255));
                break;

            case R.id.nextButton:
                index = (index + 1) % quizModel.questionsList.size();
                img_counter++;

                questionView.setText((index+1)+ ". " + quizModel.questionsList.get(index));
                if(img_counter % 2 == 0){
                    image.setImageResource(R.mipmap.brain2);
                } else {
                    image.setImageResource(R.mipmap.brain1);
                }

                if (map.containsKey(index)) {
                    int weight = map.get(index);
                    switch (weight) {
                        case 1: option1selected();
                            break;

                        case 2: option2selected();
                            break;

                        case 3: option3selected();
                            break;

                        case 4: option4selected();
                            break;

                        case 5: option5selected();
                            break;
                    }
                } else {
                    noOptionSelected();
                }
                break;


            case R.id.prevButton:
                if(index == 0)
                    index = quizModel.questionsList.size()-1;
                else
                    index -=1;
                img_counter++;

                questionView.setText((index+1)+ ". " + quizModel.questionsList.get(index));
                if(img_counter % 2 == 0){
                    image.setImageResource(R.mipmap.brain2);
                } else {
                    image.setImageResource(R.mipmap.brain1);
                }

                if (map.containsKey(index)) {
                    int weight = map.get(index);
                    switch (weight) {
                        case 1: option1selected();
                            break;

                        case 2: option2selected();
                            break;

                        case 3: option3selected();
                            break;

                        case 4: option4selected();
                            break;

                        case 5: option5selected();
                            break;
                    }
                } else {
                    noOptionSelected();
                }
                break;


            case R.id.submit:
                if(!(map.size() == TOTAL_QUESTIONS) ){
                    Toast.makeText(this, "Attempt all questions first!!", Toast.LENGTH_LONG).show();
                } else {
                    //First calculate result
                    ArrayList<Integer> sortedResult = new ArrayList<>();
                    //Sort the hashmap according to keys(i.e index) and store only values in list
                    for(int i=0;i<42;i++){
                        sortedResult.add(map.get(i));
                    }
                    result = Helper.calcInterest(sortedResult);
                    loadingResultDialog.show();

                    //Then push to backend
                    RaisecModel raisecModel = new RaisecModel();
                    raisecModel.setrResult(result.get(0));
                    raisecModel.setaResult(result.get(1));
                    raisecModel.setiResult(result.get(2));
                    raisecModel.setsResult(result.get(3));
                    raisecModel.seteResult(result.get(4));
                    raisecModel.setcResult(result.get(5));

                    UserModel userModel = new UserModel();
                    userModel.setUsername(Constants.Username);
                    userModel.setRaisecResult(raisecModel);

                    Call<StatusModel> call2 = communicator.storeRaisecResult(userModel);
                    call2.enqueue(new StoreResultHandler());
                }
                break;
        }
    }

    private class QuestionGetterHandler implements Callback<List<RaisecQuestionModel>> {
        @Override
        public void onResponse(Call<List<RaisecQuestionModel>> call, Response<List<RaisecQuestionModel>> response) {
            List<RaisecQuestionModel> raisecQuestionModels = response.body();

            for(RaisecQuestionModel questionModel : raisecQuestionModels)
                questionsList.add(questionModel.getQuestion());

            quizModel.populateQuestionsList(questionsList);
            questionView.setText((index+1)+ ". " + quizModel.questionsList.get(index));
            loadingQuestionDialog.dismiss();
        }
        @Override
        public void onFailure(Call<List<RaisecQuestionModel>> call, Throwable t) {
            d("TAG","Error :"+t.getMessage());
            loadingQuestionDialog.dismiss();
            Toast.makeText(InterestTestLayout.this, "Error! No Internet..", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    private class StoreResultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();

            if(statusModel.getStatus().equals("Success")){
                Intent intent = new Intent(InterestTestLayout.this, InterestResult.class);
                intent.putIntegerArrayListExtra("result",result);
                startActivity(intent);
            } else {
                Toast.makeText(InterestTestLayout.this, "Error!!", Toast.LENGTH_LONG).show();
            }
            finish();
            loadingResultDialog.dismiss();
        }
        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            d("TAG","Error :"+t.getMessage());
            loadingResultDialog.dismiss();
            Toast.makeText(InterestTestLayout.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    public void option1selected() {
        option2.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option3.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option4.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option5.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option1.setBackgroundColor(getResources().getColor(R.color.green));
        option2.setTextColor(getResources().getColor(R.color.textcolor));
        option3.setTextColor(getResources().getColor(R.color.textcolor));
        option4.setTextColor(getResources().getColor(R.color.textcolor));
        option5.setTextColor(getResources().getColor(R.color.textcolor));
        option1.setTextColor(getResources().getColor(R.color.white));
    }

    public void option2selected() {
        option1.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option3.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option4.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option5.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option2.setBackgroundColor(getResources().getColor(R.color.green));
        option1.setTextColor(getResources().getColor(R.color.textcolor));
        option3.setTextColor(getResources().getColor(R.color.textcolor));
        option4.setTextColor(getResources().getColor(R.color.textcolor));
        option5.setTextColor(getResources().getColor(R.color.textcolor));
        option2.setTextColor(getResources().getColor(R.color.white));
    }

    public void option3selected() {
        option1.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option2.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option4.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option5.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option3.setBackgroundColor(getResources().getColor(R.color.green));
        option1.setTextColor(getResources().getColor(R.color.textcolor));
        option2.setTextColor(getResources().getColor(R.color.textcolor));
        option4.setTextColor(getResources().getColor(R.color.textcolor));
        option5.setTextColor(getResources().getColor(R.color.textcolor));
        option3.setTextColor(getResources().getColor(R.color.white));
    }

    public void option4selected() {
        option1.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option2.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option3.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option5.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option4.setBackgroundColor(getResources().getColor(R.color.green));
        option1.setTextColor(getResources().getColor(R.color.textcolor));
        option2.setTextColor(getResources().getColor(R.color.textcolor));
        option3.setTextColor(getResources().getColor(R.color.textcolor));
        option5.setTextColor(getResources().getColor(R.color.textcolor));
        option4.setTextColor(getResources().getColor(R.color.white));
    }

    public void option5selected(){
        option1.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option2.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option3.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option4.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option5.setBackgroundColor(getResources().getColor(R.color.green));
        option1.setTextColor(getResources().getColor(R.color.textcolor));
        option2.setTextColor(getResources().getColor(R.color.textcolor));
        option3.setTextColor(getResources().getColor(R.color.textcolor));
        option4.setTextColor(getResources().getColor(R.color.textcolor));
        option5.setTextColor(getResources().getColor(R.color.white));
    }

    public void noOptionSelected() {
        option1.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option2.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option3.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option4.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option5.setBackgroundColor(getResources().getColor(R.color.optiongreen));
        option1.setTextColor(getResources().getColor(R.color.textcolor));
        option2.setTextColor(getResources().getColor(R.color.textcolor));
        option3.setTextColor(getResources().getColor(R.color.textcolor));
        option4.setTextColor(getResources().getColor(R.color.textcolor));
        option5.setTextColor(getResources().getColor(R.color.textcolor));
    }
}