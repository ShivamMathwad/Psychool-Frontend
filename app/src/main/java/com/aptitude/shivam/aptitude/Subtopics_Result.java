package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aptitude.shivam.aptitude.Model.AptitudeQuestionModel;
import com.aptitude.shivam.aptitude.Model.StatusModel;
import com.aptitude.shivam.aptitude.Model.UserModelGrad;
import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Constants;
import com.aptitude.shivam.aptitude.Utils.Helper;

import java.util.List;

public class Subtopics_Result extends AppCompatActivity implements View.OnClickListener {

    CardView computer, mechanical, aerospace;
    Dialog loadingDialog;
    NetworkClient.ServerCommunicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtopics_result);

        loadingDialog = Helper.createDialog(this, R.layout.loading_dialog,"Getting your results from database");

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
        UserModelGrad userModel = new UserModelGrad();
        userModel.setUsername(Constants.Username);
        loadingDialog.show();

        switch (v.getId()){
            case R.id.computer:
                Call<StatusModel> call = communicator.getComputerResult(userModel);
                call.enqueue(new ComputerResultHandler());
                break;

            case R.id.mechanical:
                Call<StatusModel> call2 = communicator.getMechanicalResult(userModel);
                call2.enqueue(new MechanicalResultHandler());
                break;

            case R.id.aerospace:
                Call<StatusModel> call3 = communicator.getAerospaceResult(userModel);
                call3.enqueue(new AerospaceResultHandler());
                break;
        }
    }

    private class ComputerResultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();
            //Check if test is given
            if(statusModel.getStatus().equals("Test Not Given")){
                Toast.makeText(Subtopics_Result.this, "Test not given, please give the test first!", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(Subtopics_Result.this, AptitudeResult.class);
                intent.putExtra("result", statusModel.getResult());
                intent.putExtra("testType", "CSE/IT Aptitude");
                intent.putExtra("description", "");
                startActivity(intent);
                finish();
            }
            loadingDialog.dismiss();
        }
        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            Log.d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(Subtopics_Result.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class MechanicalResultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();
            //Check if test is given
            if(statusModel.getStatus().equals("Test Not Given")){
                Toast.makeText(Subtopics_Result.this, "Test not given, please give the test first!", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(Subtopics_Result.this, AptitudeResult.class);
                intent.putExtra("result", statusModel.getResult());
                intent.putExtra("testType", "Mechanical Engineering Aptitude");
                intent.putExtra("description", "");
                startActivity(intent);
                finish();
            }
            loadingDialog.dismiss();
        }
        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            Log.d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(Subtopics_Result.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class AerospaceResultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();
            //Check if test is given
            if(statusModel.getStatus().equals("Test Not Given")){
                Toast.makeText(Subtopics_Result.this, "Test not given, please give the test first!", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(Subtopics_Result.this, AptitudeResult.class);
                intent.putExtra("result", statusModel.getResult());
                intent.putExtra("testType", "Aerospace Engineering Aptitude");
                intent.putExtra("description", "");
                startActivity(intent);
                finish();
            }
            loadingDialog.dismiss();
        }
        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            Log.d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(Subtopics_Result.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

}