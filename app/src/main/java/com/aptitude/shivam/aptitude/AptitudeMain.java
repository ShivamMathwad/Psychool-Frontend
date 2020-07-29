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

public class AptitudeMain extends AppCompatActivity implements View.OnClickListener {

    CardView numerical, perceptual, AR, spatial, verbal;
    Dialog loadingDialog;
    NetworkClient.ServerCommunicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aptitude_main);

        loadingDialog = Helper.createDialog(this, R.layout.loading_dialog, "Preparing your test");

        init();
    }

    public void init(){
        numerical = findViewById(R.id.numerical);
        perceptual = findViewById(R.id.perceptual);
        AR = findViewById(R.id.AR);
        spatial = findViewById(R.id.spatial);
        verbal = findViewById(R.id.verbal);

        numerical.setOnClickListener(this);
        perceptual.setOnClickListener(this);
        AR.setOnClickListener(this);
        spatial.setOnClickListener(this);
        verbal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);

        switch (v.getId()){
            case R.id.numerical:
                loadingDialog.show();

                Constants.test_type = "NA";
                Call<List<AptitudeQuestionModel>> call = communicator.getNAQuestions();
                call.enqueue(new NAQuestionHandler());
                break;

            case R.id.perceptual:
                loadingDialog.show();

                Constants.test_type = "PA";
                Call<List<AptitudeQuestionModel>> call2 = communicator.getPAQuestions();
                call2.enqueue(new PAQuestionHandler());
                break;

            case R.id.verbal:
                loadingDialog.show();

                Constants.test_type = "VR";
                Call<List<AptitudeQuestionModel>> call3 = communicator.getVRQuestions();
                call3.enqueue(new VRQuestionHandler());
                break;

            case R.id.AR:
                Constants.test_type = "AR";
                finish();
                startActivity(new Intent(AptitudeMain.this, AR_Instructions.class));
                break;

            case R.id.spatial:
                Constants.test_type = "SA";
                finish();
                startActivity(new Intent(AptitudeMain.this, SA_Instructions.class));
                break;
        }
    }

    private class NAQuestionHandler implements Callback<List<AptitudeQuestionModel>> {
        @Override
        public void onResponse(Call<List<AptitudeQuestionModel>> call, Response<List<AptitudeQuestionModel>> response) {
            List<AptitudeQuestionModel> aptitudeQuestionModel = response.body();
            Constants.allQuestionsAndOptions = aptitudeQuestionModel;
            Intent intent = new Intent(AptitudeMain.this, StartInstructions.class);
            intent.putExtra("text", Constants.NA_instructions);
            startActivity(intent);
            loadingDialog.dismiss();
            finish();
        }
        @Override
        public void onFailure(Call<List<AptitudeQuestionModel>> call, Throwable t) {
            d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(AptitudeMain.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }


    private class PAQuestionHandler implements Callback<List<AptitudeQuestionModel>> {
        @Override
        public void onResponse(Call<List<AptitudeQuestionModel>> call, Response<List<AptitudeQuestionModel>> response) {
            List<AptitudeQuestionModel> aptitudeQuestionModel = response.body();
            Constants.allQuestionsAndOptions = aptitudeQuestionModel;
            Intent intent = new Intent(AptitudeMain.this, StartInstructions.class);
            intent.putExtra("text", Constants.PA_instructions);
            startActivity(intent);
            loadingDialog.dismiss();
            finish();
        }
        @Override
        public void onFailure(Call<List<AptitudeQuestionModel>> call, Throwable t) {
            d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(AptitudeMain.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }


    private class VRQuestionHandler implements Callback<List<AptitudeQuestionModel>> {
        @Override
        public void onResponse(Call<List<AptitudeQuestionModel>> call, Response<List<AptitudeQuestionModel>> response) {
            List<AptitudeQuestionModel> aptitudeQuestionModel = response.body();
            Constants.allQuestionsAndOptions = aptitudeQuestionModel;
            Intent intent = new Intent(AptitudeMain.this, StartInstructions.class);
            intent.putExtra("text", Constants.VR_instructions);
            startActivity(intent);
            loadingDialog.dismiss();
            finish();
        }
        @Override
        public void onFailure(Call<List<AptitudeQuestionModel>> call, Throwable t) {
            d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(AptitudeMain.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

}
