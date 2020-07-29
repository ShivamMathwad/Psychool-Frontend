package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartInstructions extends AppCompatActivity implements View.OnClickListener {

    String message;
    TextView textView;
    Button startTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_instructions);

        init();

        message = getIntent().getStringExtra("text");
        textView.setText(message);
    }

    public void init(){
        textView = findViewById(R.id.textview);
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
