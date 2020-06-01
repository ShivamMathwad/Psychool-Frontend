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
