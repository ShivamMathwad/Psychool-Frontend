package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aptitude.shivam.aptitude.Model.AptitudeQuestionModel;
import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Constants;
import com.aptitude.shivam.aptitude.Utils.Helper;

import java.util.List;

import static android.util.Log.d;

public class Subtopics_Main extends AppCompatActivity implements View.OnClickListener {

    CardView computer, mechanical, aerospace;
    Dialog loadingDialog;
    NetworkClient.ServerCommunicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtopics_main);

        loadingDialog = Helper.createDialog(this, R.layout.loading_dialog, "Preparing your test");

        init();
    }

    public void init(){
        computer = findViewById(R.id.computer);
        mechanical = findViewById(R.id.mechanical);
        aerospace = findViewById(R.id.aerospace);

        computer.setOnClickListener(this);
        mechanical.setOnClickListener(this);
        aerospace.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);
        loadingDialog.show();

        switch (v.getId()){
            case R.id.computer:
                Constants.test_type = "Computer";
                Call<List<AptitudeQuestionModel>> call = communicator.getComputerQuestions();
                call.enqueue(new ComputerQuestionHandler());
                break;

            case R.id.mechanical:
                Constants.test_type = "Mechanical";
                Call<List<AptitudeQuestionModel>> call2 = communicator.getMechanicalQuestions();
                call2.enqueue(new MechanicalQuestionHandler());
                break;

            case R.id.aerospace:
                Constants.test_type = "Aerospace";
                Call<List<AptitudeQuestionModel>> call3 = communicator.getAerospaceQuestions();
                call3.enqueue(new AerospaceQuestionHandler());
                break;
        }
    }

    private class ComputerQuestionHandler implements Callback<List<AptitudeQuestionModel>> {
        @Override
        public void onResponse(Call<List<AptitudeQuestionModel>> call, Response<List<AptitudeQuestionModel>> response) {
            List<AptitudeQuestionModel> questionModel = response.body();
            Constants.allQuestionsAndOptions = questionModel;
            startActivity(new Intent(Subtopics_Main.this, GradTestLayout.class));
            loadingDialog.dismiss();
            finish();
        }
        @Override
        public void onFailure(Call<List<AptitudeQuestionModel>> call, Throwable t) {
            d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(Subtopics_Main.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class MechanicalQuestionHandler implements Callback<List<AptitudeQuestionModel>> {
        @Override
        public void onResponse(Call<List<AptitudeQuestionModel>> call, Response<List<AptitudeQuestionModel>> response) {
            List<AptitudeQuestionModel> questionModel = response.body();
            Constants.allQuestionsAndOptions = questionModel;
            startActivity(new Intent(Subtopics_Main.this, GradTestLayout.class));
            loadingDialog.dismiss();
            finish();
        }
        @Override
        public void onFailure(Call<List<AptitudeQuestionModel>> call, Throwable t) {
            d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(Subtopics_Main.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class AerospaceQuestionHandler implements Callback<List<AptitudeQuestionModel>> {
        @Override
        public void onResponse(Call<List<AptitudeQuestionModel>> call, Response<List<AptitudeQuestionModel>> response) {
            List<AptitudeQuestionModel> questionModel = response.body();
            Constants.allQuestionsAndOptions = questionModel;
            startActivity(new Intent(Subtopics_Main.this, GradTestLayout.class));
            loadingDialog.dismiss();
            finish();
        }
        @Override
        public void onFailure(Call<List<AptitudeQuestionModel>> call, Throwable t) {
            d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(Subtopics_Main.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

}