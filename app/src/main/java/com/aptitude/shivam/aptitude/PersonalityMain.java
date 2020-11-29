package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PersonalityMain extends AppCompatActivity implements View.OnClickListener {

    CardView personality, interest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_main);

        init();
    }

    public void init(){
        personality = findViewById(R.id.personality);
        interest = findViewById(R.id.interest);

        personality.setOnClickListener(this);
        interest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personality:
                startActivity(new Intent(PersonalityMain.this, Quiz_layout.class));
                break;

            case R.id.interest:
                startActivity(new Intent(PersonalityMain.this, InterestTestLayout.class));
                break;
        }
    }

}