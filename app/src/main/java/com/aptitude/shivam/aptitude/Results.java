package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aptitude.shivam.aptitude.Model.OceanModel;
import com.aptitude.shivam.aptitude.Model.StatusModel;
import com.aptitude.shivam.aptitude.Model.UserModel;
import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Constants;
import com.aptitude.shivam.aptitude.Utils.Helper;

import java.util.ArrayList;

import static android.util.Log.d;

public class Results extends AppCompatActivity implements View.OnClickListener {

    CardView personality_result, numerical_result, perceptual_result, abstract_result, spatial_result, verbal_result;
    Dialog loadingDialog;
    NetworkClient.ServerCommunicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        loadingDialog = Helper.createDialog(this, R.layout.loading_dialog,"Getting your results from database");

        init();
    }

    public void init(){
        personality_result = findViewById(R.id.personality_result);
        numerical_result = findViewById(R.id.numerical_result);
        perceptual_result = findViewById(R.id.perceptual_result);
        abstract_result = findViewById(R.id.abstract_result);
        spatial_result = findViewById(R.id.spatial_result);
        verbal_result = findViewById(R.id.verbal_result);

        personality_result.setOnClickListener(this);
        numerical_result.setOnClickListener(this);
        perceptual_result.setOnClickListener(this);
        abstract_result.setOnClickListener(this);
        spatial_result.setOnClickListener(this);
        verbal_result.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);
        UserModel userModel = new UserModel();
        userModel.setUsername(Constants.Username);
        loadingDialog.show();

        switch (v.getId()){
            case R.id.personality_result:
                Call<OceanModel> call = communicator.getOceanResult(userModel);
                call.enqueue(new OceanResultHandler());
                break;

            case R.id.numerical_result:
                Call<StatusModel> call2 = communicator.getNAresult(userModel);
                call2.enqueue(new NAresultHandler());
                break;

            case R.id.perceptual_result:
                Call<StatusModel> call3 = communicator.getPAresult(userModel);
                call3.enqueue(new PAresultHandler());
                break;

            case R.id.verbal_result:
                Call<StatusModel> call4 = communicator.getVRresult(userModel);
                call4.enqueue(new VRresultHandler());
                break;

            case R.id.abstract_result:
                Call<StatusModel> call5 = communicator.getARresult(userModel);
                call5.enqueue(new ARresultHandler());
                break;

            case R.id.spatial_result:
                Call<StatusModel> call6 = communicator.getSAresult(userModel);
                call6.enqueue(new SAresultHandler());
                break;
        }
    }

    private class OceanResultHandler implements Callback<OceanModel> {
        @Override
        public void onResponse(Call<OceanModel> call, Response<OceanModel> response) {
            OceanModel oceanModel = response.body();

            //All traits zero means user has not given the test
            if(oceanModel.getOResult()==0 && oceanModel.getCResult()==0 && oceanModel.getEResult()==0 && oceanModel.getAResult()==0 && oceanModel.getNResult()==0){
                Toast.makeText(Results.this, "Test not given, please give the test first!", Toast.LENGTH_LONG).show();
            } else {
                ArrayList<Integer> result = new ArrayList<>();
                result.add(oceanModel.getOResult());
                result.add(oceanModel.getCResult());
                result.add(oceanModel.getEResult());
                result.add(oceanModel.getAResult());
                result.add(oceanModel.getNResult());
                Intent intent = new Intent(Results.this, OceanResult.class);
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
            Toast.makeText(Results.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class NAresultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();

            //Check if test is given
            if(statusModel.getStatus().equals("Test Not Given")){
                Toast.makeText(Results.this, "Test not given, please give the test first!", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(Results.this, AptitudeResult.class);
                intent.putExtra("result", statusModel.getResult());
                intent.putExtra("testType", "Numerical Aptitude");
                intent.putExtra("description", Constants.NA_description);
                startActivity(intent);
                finish();
            }
            loadingDialog.dismiss();
        }
        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            Log.d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(Results.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class PAresultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();

            //Check if test is given
            if(statusModel.getStatus().equals("Test Not Given")){
                Toast.makeText(Results.this, "Test not given, please give the test first!", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(Results.this, AptitudeResult.class);
                intent.putExtra("result", statusModel.getResult());
                intent.putExtra("testType", "Perceptual Aptitude");
                intent.putExtra("description", Constants.PA_description);
                startActivity(intent);
                finish();
            }
            loadingDialog.dismiss();
        }
        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            Log.d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(Results.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class VRresultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();

            //Check if test is given
            if(statusModel.getStatus().equals("Test Not Given")){
                Toast.makeText(Results.this, "Test not given, please give the test first!", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(Results.this, AptitudeResult.class);
                intent.putExtra("result", statusModel.getResult());
                intent.putExtra("testType", "Verbal Reasoning");
                intent.putExtra("description", Constants.VR_description);
                startActivity(intent);
                finish();
            }
            loadingDialog.dismiss();
        }
        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            Log.d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(Results.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class ARresultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();

            //Check if test is given
            if(statusModel.getStatus().equals("Test Not Given")){
                Toast.makeText(Results.this, "Test not given, please give the test first!", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(Results.this, AptitudeResult.class);
                intent.putExtra("result", statusModel.getResult());
                intent.putExtra("testType", "Abstract Reasoning");
                intent.putExtra("description", Constants.AR_description);
                startActivity(intent);
                finish();
            }
            loadingDialog.dismiss();
        }
        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            Log.d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(Results.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class SAresultHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();

            //Check if test is given
            if(statusModel.getStatus().equals("Test Not Given")){
                Toast.makeText(Results.this, "Test not given, please give the test first!", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(Results.this, AptitudeResult.class);
                intent.putExtra("result", statusModel.getResult());
                intent.putExtra("testType", "Spatial Aptitude");
                intent.putExtra("description", Constants.SA_description);
                startActivity(intent);
                finish();
            }
            loadingDialog.dismiss();
        }
        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            Log.d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(Results.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

}
