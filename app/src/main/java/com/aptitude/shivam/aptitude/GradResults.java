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

import com.aptitude.shivam.aptitude.Model.OceanModel;
import com.aptitude.shivam.aptitude.Model.StatusModel;
import com.aptitude.shivam.aptitude.Model.UserModel;
import com.aptitude.shivam.aptitude.Model.UserModelGrad;
import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Constants;
import com.aptitude.shivam.aptitude.Utils.Helper;

import java.util.ArrayList;

public class GradResults extends AppCompatActivity implements View.OnClickListener{

    CardView engineering, medical, political, management, personality;
    Dialog loadingDialog;
    NetworkClient.ServerCommunicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grad_results);

        loadingDialog = Helper.createDialog(this, R.layout.loading_dialog,"Getting your results from database");

        init();
    }

    public void init(){
        engineering = findViewById(R.id.engineering);
        medical = findViewById(R.id.medical);
        political = findViewById(R.id.political);
        management = findViewById(R.id.management);
        personality = findViewById(R.id.personality);

        engineering.setOnClickListener(this);
        medical.setOnClickListener(this);
        political.setOnClickListener(this);
        management.setOnClickListener(this);
        personality.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);
        UserModelGrad userModel = new UserModelGrad();
        userModel.setUsername(Constants.Username);
        loadingDialog.show();

        switch (v.getId()){
            case R.id.personality:
                UserModel model = new UserModel();
                model.setUsername(Constants.Username);

                Call<OceanModel> call = communicator.getOceanResult(model);
                call.enqueue(new OceanResultHandler());
                break;

            case R.id.engineering:
                startActivity(new Intent(GradResults.this, Subtopics_Result.class));
                break;

            case R.id.medical:
                Call<StatusModel> call2 = communicator.getMedicalResult(userModel);
                call2.enqueue(new MedicalResultHandler());
                break;

            case R.id.political:
                Call<StatusModel> call3 = communicator.getPoliticalResult(userModel);
                call3.enqueue(new PoliticalResultHandler());
                break;

            case R.id.management:
                Call<StatusModel> call4 = communicator.getManagementResult(userModel);
                call4.enqueue(new ManagementResultHandler());
                break;
        }
    }

    private class OceanResultHandler implements Callback<OceanModel> {
        @Override
        public void onResponse(Call<OceanModel> call, Response<OceanModel> response) {
            OceanModel oceanModel = response.body();

            //All traits zero means user has not given the test
            if(oceanModel.getOResult()==0 && oceanModel.getCResult()==0 && oceanModel.getEResult()==0 && oceanModel.getAResult()==0 && oceanModel.getNResult()==0){
                Toast.makeText(GradResults.this, "Test not given, please give the test first!", Toast.LENGTH_LONG).show();
            } else {
                ArrayList<Integer> result = new ArrayList<>();
                result.add(oceanModel.getOResult());
                result.add(oceanModel.getCResult());
                result.add(oceanModel.getEResult());
                result.add(oceanModel.getAResult());
                result.add(oceanModel.getNResult());
                Intent intent = new Intent(GradResults.this, OceanResult.class);
                intent.putIntegerArrayListExtra("result",result);
                startActivity(intent);
                finish();
            }
            loadingDialog.dismiss();
        }
        @Override
        public void onFailure(Call<OceanModel> call, Throwable t) {
            Log.d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(GradResults.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class MedicalResultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();
            //Check if test is given
            if(statusModel.getStatus().equals("Test Not Given")){
                Toast.makeText(GradResults.this, "Test not given, please give the test first!", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(GradResults.this, AptitudeResult.class);
                intent.putExtra("result", statusModel.getResult());
                intent.putExtra("testType", "Medical Aptitude");
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
            Toast.makeText(GradResults.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class PoliticalResultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();
            //Check if test is given
            if(statusModel.getStatus().equals("Test Not Given")){
                Toast.makeText(GradResults.this, "Test not given, please give the test first!", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(GradResults.this, AptitudeResult.class);
                intent.putExtra("result", statusModel.getResult());
                intent.putExtra("testType", "Public & Political Affairs Aptitude");
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
            Toast.makeText(GradResults.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class ManagementResultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();
            //Check if test is given
            if(statusModel.getStatus().equals("Test Not Given")){
                Toast.makeText(GradResults.this, "Test not given, please give the test first!", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(GradResults.this, AptitudeResult.class);
                intent.putExtra("result", statusModel.getResult());
                intent.putExtra("testType", "Management Studies Aptitude");
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
            Toast.makeText(GradResults.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

}