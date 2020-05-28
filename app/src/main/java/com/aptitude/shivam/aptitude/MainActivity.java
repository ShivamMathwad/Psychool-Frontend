package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aptitude.shivam.aptitude.Utils.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView email;
    CardView aptitude_card,psychometric_card,result_card,profile_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //List<Integer> arr = new ArrayList<>();
        //arr = Arrays.asList(2,2,3,4,5,3,4,1,4,2,3,1,4,4,4,4,5,1,3,5,4,2,3,3,4,3,4,1,2,2,4,4,5,1,4,3,2,3,1,2,4,4,3,1,4,4,4,4,3,3);

        //Helper.calcPersonality(arr);

        email = findViewById(R.id.email);
        aptitude_card = findViewById(R.id.aptitude_card);
        psychometric_card = findViewById(R.id.psychometric_card);
        result_card = findViewById(R.id.result_card);
        profile_card = findViewById(R.id.profile_card);

        aptitude_card.setOnClickListener(this);
        psychometric_card.setOnClickListener(this);
        result_card.setOnClickListener(this);
        profile_card.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aptitude_card:
                //startActivity(new Intent(MainActivity.this,Quiz_layout.class));
                break;

            case R.id.psychometric_card:
                startActivity(new Intent(MainActivity.this, Quiz_layout.class));
                break;

            case R.id.result_card:
                //startActivity(new Intent(MainActivity.this,Quiz_layout.class));
                break;

            case R.id.profile_card:
                //startActivity(new Intent(MainActivity.this,Quiz_layout.class));
                break;
        }
    }
}
