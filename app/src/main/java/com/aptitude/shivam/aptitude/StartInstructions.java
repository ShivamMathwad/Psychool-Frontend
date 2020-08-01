package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aptitude.shivam.aptitude.Utils.Constants;

public class StartInstructions extends AppCompatActivity implements View.OnClickListener {

    String message;
    TextView textView, test_title;
    CardView startTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_instructions);

        init();

        if(Constants.test_type.equals("NA")){
            test_title.setText("Numerical Aptitude");
        } else if(Constants.test_type.equals("PA")){
            test_title.setText("Perceptual Aptitude");
        } else{
            test_title.setText("Verbal Reasoning");
        }
        message = getIntent().getStringExtra("text");
        textView.setText(message);
    }

    public void init(){
        textView = findViewById(R.id.textview);
        test_title = findViewById(R.id.test_title);
        startTest = findViewById(R.id.startTest);

        startTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.startTest:
                finish();
                startActivity(new Intent(StartInstructions.this, AptitudeQuizLayout.class));
                break;
        }
    }

}
