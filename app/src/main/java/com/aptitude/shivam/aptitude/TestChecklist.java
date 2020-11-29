package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.aptitude.shivam.aptitude.Model.AllScoresModel;
import com.aptitude.shivam.aptitude.Model.TestStatusModel;
import com.aptitude.shivam.aptitude.Model.UserModel;
import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Constants;
import com.aptitude.shivam.aptitude.Utils.Helper;

import static android.util.Log.d;

public class TestChecklist extends AppCompatActivity implements View.OnClickListener {

    boolean personality_bool, NA_bool, PA_bool, VR_bool, AR_bool, SA_bool;
    ImageView personality, numerical, perceptual, verbal, abstractApti, spatial;
    Button submit;
    Dialog loadingDialog;
    NetworkClient.ServerCommunicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_checklist);

        loadingDialog = Helper.createDialog(this, R.layout.loading_dialog, "Fetching all your scores");

        init();

        personality_bool = getIntent().getBooleanExtra("personality",false);
        NA_bool = getIntent().getBooleanExtra("numerical",false);
        PA_bool = getIntent().getBooleanExtra("perceptual",false);
        VR_bool = getIntent().getBooleanExtra("verbal",false);
        AR_bool = getIntent().getBooleanExtra("abstract",false);
        SA_bool = getIntent().getBooleanExtra("spatial",false);

        if(personality_bool){
            personality.setImageResource(R.mipmap.green_tick);
        }
        if(NA_bool){
            numerical.setImageResource(R.mipmap.green_tick);
        }
        if(PA_bool){
            perceptual.setImageResource(R.mipmap.green_tick);
        }
        if(VR_bool){
            verbal.setImageResource(R.mipmap.green_tick);
        }
        if(AR_bool){
            abstractApti.setImageResource(R.mipmap.green_tick);
        }
        if(SA_bool){
            spatial.setImageResource(R.mipmap.green_tick);
        }
    }

    public void init(){
        personality = findViewById(R.id.personality_img);
        numerical = findViewById(R.id.numerical_img);
        perceptual = findViewById(R.id.perceptual_img);
        verbal = findViewById(R.id.verbal_img);
        abstractApti = findViewById(R.id.abstract_img);
        spatial = findViewById(R.id.spatial_img);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
                if(personality_bool==true && NA_bool==true && PA_bool==true && VR_bool==true && AR_bool==true && SA_bool==true){
                    loadingDialog.show();

                    UserModel userModel = new UserModel();
                    userModel.setUsername(Constants.Username);
                    communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);
                    Call<AllScoresModel> call = communicator.getAllScores(userModel);
                    call.enqueue(new AllResultHandler());
                } else {
                    Toast.makeText(TestChecklist.this, "Complete the checklist to proceed!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private class AllResultHandler implements Callback<AllScoresModel> {
        @Override
        public void onResponse(Call<AllScoresModel> call, Response<AllScoresModel> response) {
            AllScoresModel model = response.body();

            Intent intent = new Intent(TestChecklist.this,CareerRecommend.class);

            double numerical, perceptual, verbal, abstractApti, spatial;

            if(model.getNumerical()<=30){
                numerical = 0.16;
            }else if(model.getNumerical()>30 && model.getNumerical()<=73){
                numerical = 0.5;
            }else{
                numerical = 0.88;
            }

            if(model.getPerceptual()<=40){
                perceptual = 0.16;
            }else if(model.getPerceptual()>40 && model.getPerceptual()<=83){
                perceptual = 0.5;
            }else{
                perceptual = 0.88;
            }

            if(model.getVerbal()<=33){
                verbal = 0.16;
            }else if(model.getVerbal()>33 && model.getVerbal()<=73){
                verbal = 0.5;
            }else{
                verbal = 0.88;
            }

            if(model.getAbstractApti()<=26){
                abstractApti = 0.16;
            }else if(model.getAbstractApti()>26 && model.getAbstractApti()<=70){
                abstractApti = 0.5;
            }else{
                abstractApti = 0.88;
            }

            if(model.getSpatial()<=23){
                spatial = 0.16;
            }else if(model.getSpatial()>23 && model.getSpatial()<=60){
                spatial = 0.5;
            }else{
                spatial = 0.88;
            }

            intent.putExtra("o_result",(model.getO_result()/100) );
            intent.putExtra("c_result",(model.getC_result()/100) );
            intent.putExtra("e_result",(model.getE_result()/100) );
            intent.putExtra("a_result",(model.getA_result()/100) );
            intent.putExtra("numerical",numerical);
            intent.putExtra("abstract",abstractApti);
            intent.putExtra("perceptual",perceptual);
            intent.putExtra("verbal",verbal);
            intent.putExtra("spatial",spatial);
            startActivity(intent);
            finish();
            loadingDialog.dismiss();
        }

        @Override
        public void onFailure(Call<AllScoresModel> call, Throwable t) {
            d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(TestChecklist.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }
}