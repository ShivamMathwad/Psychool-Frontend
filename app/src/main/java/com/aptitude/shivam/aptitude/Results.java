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

import java.util.ArrayList;

import static android.util.Log.d;

public class Results extends AppCompatActivity implements View.OnClickListener {

    CardView personality_result,aptitude_result;
    Dialog loadingDialog;
    NetworkClient.ServerCommunicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Getting your results from database");
        builder.setMessage("Please Wait...");
        loadingDialog = builder.create();
        loadingDialog.setCanceledOnTouchOutside(false);

        init();
    }

    public void init(){
        personality_result = findViewById(R.id.personality_result);
        aptitude_result = findViewById(R.id.aptitude_result);

        personality_result.setOnClickListener(this);
        aptitude_result.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.personality_result:
                loadingDialog.show();

                UserModel userModel = new UserModel();
                userModel.setUsername(Constants.Username);

                communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);
                Call<OceanModel> call = communicator.getOceanResult(userModel);
                call.enqueue(new OceanResultHandler());
                break;

            case R.id.aptitude_result:
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
            }
            loadingDialog.dismiss();
        }

        @Override
        public void onFailure(Call<OceanModel> call, Throwable t) {
            d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(Results.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }
}
