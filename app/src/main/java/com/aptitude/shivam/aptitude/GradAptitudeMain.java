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
import android.view.View;
import android.widget.Toast;

import com.aptitude.shivam.aptitude.Model.AptitudeQuestionModel;
import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Constants;
import com.aptitude.shivam.aptitude.Utils.Helper;

import java.util.List;

import static android.util.Log.d;

public class GradAptitudeMain extends AppCompatActivity implements View.OnClickListener {

    CardView engineering, medical, political, management;
    Dialog loadingDialog;
    NetworkClient.ServerCommunicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grad_aptitude_main);

        loadingDialog = Helper.createDialog(this, R.layout.loading_dialog, "Preparing your test");

        init();
    }

    public void init(){
        engineering = findViewById(R.id.engineering);
        medical = findViewById(R.id.medical);
        political = findViewById(R.id.political);
        management = findViewById(R.id.management);

        engineering.setOnClickListener(this);
        medical.setOnClickListener(this);
        political.setOnClickListener(this);
        management.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);

        switch (v.getId()){
            case R.id.engineering:
                startActivity(new Intent(GradAptitudeMain.this, Subtopics_Main.class));
                break;

            case R.id.medical:
                loadingDialog.show();

                Constants.test_type = "Medical";
                Call<List<AptitudeQuestionModel>> call = communicator.getMedicalQuestions();
                call.enqueue(new MedicalQuestionHandler());
                break;

            case R.id.political:
                loadingDialog.show();

                Constants.test_type = "Political";
                Call<List<AptitudeQuestionModel>> call2 = communicator.getPoliticalQuestions();
                call2.enqueue(new PoliticalQuestionHandler());
                break;

            case R.id.management:
                loadingDialog.show();

                Constants.test_type = "Management";
                Call<List<AptitudeQuestionModel>> call3 = communicator.getManagementQuestions();
                call3.enqueue(new ManagementQuestionHandler());
                break;
        }
    }

    private class MedicalQuestionHandler implements Callback<List<AptitudeQuestionModel>> {
        @Override
        public void onResponse(Call<List<AptitudeQuestionModel>> call, Response<List<AptitudeQuestionModel>> response) {
            List<AptitudeQuestionModel> questionModel = response.body();
            Constants.allQuestionsAndOptions = questionModel;
            startActivity(new Intent(GradAptitudeMain.this, GradTestLayout.class));
            loadingDialog.dismiss();
            finish();
        }
        @Override
        public void onFailure(Call<List<AptitudeQuestionModel>> call, Throwable t) {
            d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(GradAptitudeMain.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class PoliticalQuestionHandler implements Callback<List<AptitudeQuestionModel>> {
        @Override
        public void onResponse(Call<List<AptitudeQuestionModel>> call, Response<List<AptitudeQuestionModel>> response) {
            List<AptitudeQuestionModel> questionModel = response.body();
            Constants.allQuestionsAndOptions = questionModel;
            startActivity(new Intent(GradAptitudeMain.this, GradTestLayout.class));
            loadingDialog.dismiss();
            finish();
        }
        @Override
        public void onFailure(Call<List<AptitudeQuestionModel>> call, Throwable t) {
            d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(GradAptitudeMain.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class ManagementQuestionHandler implements Callback<List<AptitudeQuestionModel>> {
        @Override
        public void onResponse(Call<List<AptitudeQuestionModel>> call, Response<List<AptitudeQuestionModel>> response) {
            List<AptitudeQuestionModel> questionModel = response.body();
            Constants.allQuestionsAndOptions = questionModel;
            startActivity(new Intent(GradAptitudeMain.this, GradTestLayout.class));
            loadingDialog.dismiss();
            finish();
        }
        @Override
        public void onFailure(Call<List<AptitudeQuestionModel>> call, Throwable t) {
            d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(GradAptitudeMain.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

}