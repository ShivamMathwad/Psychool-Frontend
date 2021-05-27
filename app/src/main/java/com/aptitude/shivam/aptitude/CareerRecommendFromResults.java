package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Helper;

import java.util.ArrayList;
import java.util.List;

public class CareerRecommendFromResults extends AppCompatActivity implements View.OnClickListener {
    CardView careerCard1, careerCard2, careerCard3;
    TextView career1, career2, career3;
    String str_career1, str_career2, str_career3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_recommend_from_results);

        init();

        ArrayList<String> careerNames = getIntent().getStringArrayListExtra("recommendList");

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
        Intent intent = new Intent(CareerRecommendFromResults.this, CareerDetails.class);

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

}