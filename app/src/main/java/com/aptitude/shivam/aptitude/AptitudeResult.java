package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AptitudeResult extends AppCompatActivity {

    TextView score, test_type, test_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aptitude_result);

        init();

        score.setText(Integer.toString(getIntent().getIntExtra("result",0))+"%");
        test_type.setText(getIntent().getStringExtra("testType"));
        test_description.setText(getIntent().getStringExtra("description"));
    }

    public void init() {
        score = findViewById(R.id.score);
        test_type = findViewById(R.id.test_type);
        test_description = findViewById(R.id.test_description);
    }

}