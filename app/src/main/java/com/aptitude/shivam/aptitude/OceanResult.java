package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aptitude.shivam.aptitude.Utils.Constants;

import java.util.ArrayList;


public class OceanResult extends AppCompatActivity {

    TextView o_description, c_description, e_description, a_description, n_description;
    ProgressBar o_progressbar, c_progressbar, e_progressbar, a_progressbar, n_progressbar;
    TextView o_progress, c_progress, e_progress, a_progress, n_progress;
    ArrayList<Integer> result = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocean_result);

        init();

        o_description.setText(Constants.O_description);
        c_description.setText(Constants.C_description);
        e_description.setText(Constants.E_description);
        a_description.setText(Constants.A_description);
        n_description.setText(Constants.N_description);

        result = getIntent().getIntegerArrayListExtra("result");

        o_progressbar.setProgress(result.get(0));
        o_progress.setText(result.get(0)+"%");
        c_progressbar.setProgress(result.get(1));
        c_progress.setText(result.get(1)+"%");
        e_progressbar.setProgress(result.get(2));
        e_progress.setText(result.get(2)+"%");
        a_progressbar.setProgress(result.get(3));
        a_progress.setText(result.get(3)+"%");
        n_progressbar.setProgress(result.get(4));
        n_progress.setText(result.get(4)+"%");
    }

    public void init(){
        o_progressbar = findViewById(R.id.O_progressbar);
        c_progressbar = findViewById(R.id.C_progressbar);
        e_progressbar = findViewById(R.id.E_progressbar);
        a_progressbar = findViewById(R.id.A_progressbar);
        n_progressbar = findViewById(R.id.N_progressbar);
        o_progress = findViewById(R.id.O_progress);
        c_progress = findViewById(R.id.C_progress);
        e_progress = findViewById(R.id.E_progress);
        a_progress = findViewById(R.id.A_progress);
        n_progress = findViewById(R.id.N_progress);
        o_description = findViewById(R.id.O_description);
        c_description = findViewById(R.id.C_description);
        e_description = findViewById(R.id.E_description);
        a_description = findViewById(R.id.A_description);
        n_description = findViewById(R.id.N_description);
    }

}


