package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aptitude.shivam.aptitude.Model.AptitudeQuestionModel;
import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Constants;

import java.util.List;

import static android.util.Log.d;

public class AptitudeMain extends AppCompatActivity implements View.OnClickListener {

    CardView numerical, perceptual;
    Dialog loadingDialog;
    NetworkClient.ServerCommunicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aptitude_main);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Preparing your test");
        builder.setMessage("Please Wait...");
        loadingDialog = builder.create();
        loadingDialog.setCanceledOnTouchOutside(false);

        init();
    }

    public void init(){
        numerical = findViewById(R.id.numerical);
        perceptual = findViewById(R.id.perceptual);

        numerical.setOnClickListener(this);
        perceptual.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);

        switch (v.getId()){
            case R.id.numerical:
                loadingDialog.show();

                Call<List<AptitudeQuestionModel>> call = communicator.getNAQuestions();
                call.enqueue(new NAQuestionHandler());
                break;

            case R.id.perceptual:
                //intent.putExtra("text", Constants.NU_instructions);
                //startActivity(intent);
                break;
        }
    }

    private class NAQuestionHandler implements Callback<List<AptitudeQuestionModel>> {
        @Override
        public void onResponse(Call<List<AptitudeQuestionModel>> call, Response<List<AptitudeQuestionModel>> response) {
            List<AptitudeQuestionModel> aptitudeQuestionModel = response.body();
            Constants.allQuestionsAndOptions = aptitudeQuestionModel;
            Intent intent = new Intent(AptitudeMain.this, StartInstructions.class);
            intent.putExtra("text", Constants.NU_instructions);
            startActivity(intent);
            loadingDialog.dismiss();
        }

        @Override
        public void onFailure(Call<List<AptitudeQuestionModel>> call, Throwable t) {
            d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(AptitudeMain.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

}
