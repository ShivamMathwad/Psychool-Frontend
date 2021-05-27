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
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aptitude.shivam.aptitude.Model.AllScoresFloatModel;
import com.aptitude.shivam.aptitude.Model.AllScoresModel;
import com.aptitude.shivam.aptitude.Model.CareerRecommendModel;
import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Constants;
import com.aptitude.shivam.aptitude.Utils.Helper;

import java.util.ArrayList;
import java.util.List;

import static android.util.Log.d;

public class CareerRecommend extends AppCompatActivity implements View.OnClickListener {
    CardView careerCard1, careerCard2, careerCard3;
    TextView career1, career2, career3;
    List<String> careerNames;
    Dialog loadingDialog;
    NetworkClient.ServerCommunicator communicator;
    String str_career1, str_career2, str_career3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_recommend);

        init();

        loadingDialog = Helper.createDialog(this, R.layout.loading_dialog, "Generating your recommendation. Please Wait! This may take a while !");
        loadingDialog.show();

        AllScoresFloatModel model = new AllScoresFloatModel();
        model.setO_result(getIntent().getDoubleExtra("o_result",0.5));
        model.setC_result(getIntent().getDoubleExtra("c_result",0.5));
        model.setE_result(getIntent().getDoubleExtra("e_result",0.5));
        model.setA_result(getIntent().getDoubleExtra("a_result",0.5));
        model.setNumerical(getIntent().getDoubleExtra("numerical",0.5));
        model.setPerceptual(getIntent().getDoubleExtra("perceptual",0.5));
        model.setVerbal(getIntent().getDoubleExtra("verbal",0.5));
        model.setAbstractApti(getIntent().getDoubleExtra("abstract",0.5));
        model.setSpatial(getIntent().getDoubleExtra("spatial",0.5));
        model.setUsername(Constants.Username);
        model.setEmail(Constants.Email);

        communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);
        Call<List<String>> call = communicator.getCareerRecommendation(model);
        call.enqueue(new CareerRecommendationHandler());
    }

    public void init() {
        career1 = findViewById(R.id.career1);
        career2 = findViewById(R.id.career2);
        career3 = findViewById(R.id.career3);
        careerCard1 = findViewById(R.id.careerCard1);
        careerCard2 = findViewById(R.id.careerCard2);
        careerCard3 = findViewById(R.id.careerCard3);

        careerCard1.setOnClickListener(this);
        careerCard2.setOnClickListener(this);
        careerCard3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(CareerRecommend.this, CareerDetails.class);

        switch (view.getId()) {
            case R.id.careerCard1:
                str_career1 = career1.getText().toString();
                intent.putExtra("career_name",str_career1);
                startActivity(intent);
                break;

            case R.id.careerCard2:
                str_career2 = career2.getText().toString();
                intent.putExtra("career_name",str_career2);
                startActivity(intent);
                break;

            case R.id.careerCard3:
                str_career3 = career3.getText().toString();
                intent.putExtra("career_name",str_career3);
                startActivity(intent);
                break;
        }
    }

    private class CareerRecommendationHandler implements Callback<List<String>>{
        @Override
        public void onResponse(Call<List<String>> call, Response<List<String>> response) {
            careerNames = response.body();

            if(careerNames.size() == 1) {
                careerCard2.setVisibility(View.GONE);
                careerCard3.setVisibility(View.GONE);
                career1.setText(careerNames.get(0));

            } else if(careerNames.size() == 2) {
                careerCard3.setVisibility(View.GONE);
                career1.setText(careerNames.get(0));
                career2.setText(careerNames.get(1));
            } else {
                career1.setText(careerNames.get(0));
                career2.setText(careerNames.get(1));
                career3.setText(careerNames.get(2));
            }

            loadingDialog.dismiss();
        }
        @Override
        public void onFailure(Call<List<String>> call, Throwable t) {
            d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(CareerRecommend.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

}