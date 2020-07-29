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
import com.aptitude.shivam.aptitude.Model.UserModel;
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
        UserModel userModel = new UserModel();
        userModel.setUsername(Constants.Username);
        loadingDialog.show();

        switch (v.getId()){
            case R.id.personality:
                Call<OceanModel> call = communicator.getOceanResult(userModel);
                call.enqueue(new OceanResultHandler());
                break;

            case R.id.engineering:
                /*
                loadingDialog.show();

                Constants.test_type = "NA";
                Call<List<AptitudeQuestionModel>> call = communicator.getNAQuestions();
                call.enqueue(new AptitudeMain.NAQuestionHandler());*/
                break;

            case R.id.medical:
                break;

            case R.id.political:
                break;

            case R.id.management:
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

}