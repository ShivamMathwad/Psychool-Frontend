package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class TestChecklist extends AppCompatActivity implements View.OnClickListener {

    boolean personality_bool, NA_bool, PA_bool, VR_bool, AR_bool, SA_bool;
    ImageView personality, numerical, perceptual, verbal, abstractApti, spatial;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_checklist);

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
                    //intent
                } else {
                    Toast.makeText(TestChecklist.this, "Complete the checklist to proceed!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

}