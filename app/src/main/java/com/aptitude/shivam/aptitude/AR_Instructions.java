package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AR_Instructions extends AppCompatActivity implements View.OnClickListener {

    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_instructions);

        submit = findViewById(R.id.startTest);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startTest:
                finish();
                startActivity(new Intent(AR_Instructions.this, ARandSATest.class));
                break;
        }
    }

}