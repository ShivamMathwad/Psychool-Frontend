package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aptitude.shivam.aptitude.Utils.Constants;

import java.util.ArrayList;

public class InterestResult extends AppCompatActivity {

    TextView r_description, i_description, a_description, s_description, e_description, c_description;
    ProgressBar r_progressbar, i_progressbar, a_progressbar, s_progressbar, e_progressbar, c_progressbar;
    TextView r_progress, i_progress, a_progress, s_progress, e_progress, c_progress;
    ArrayList<Integer> result = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_result);

        init();

        /*
        r_description.setText(Constants.O_description);
        i_description.setText(Constants.O_description);
        a_description.setText(Constants.A_description);
        s_description.setText(Constants.N_description);
        e_description.setText(Constants.E_description);
        c_description.setText(Constants.C_description);
         */

        result = getIntent().getIntegerArrayListExtra("result");

        r_progressbar.setProgress(result.get(0));
        r_progress.setText(result.get(0)+"%");
        a_progressbar.setProgress(result.get(1));
        a_progress.setText(result.get(1)+"%");
        i_progressbar.setProgress(result.get(2));
        i_progress.setText(result.get(2)+"%");
        s_progressbar.setProgress(result.get(3));
        s_progress.setText(result.get(3)+"%");
        e_progressbar.setProgress(result.get(4));
        e_progress.setText(result.get(4)+"%");
        c_progressbar.setProgress(result.get(5));
        c_progress.setText(result.get(5)+"%");
    }

    public void init(){
        r_progressbar = findViewById(R.id.R_progressbar);
        i_progressbar = findViewById(R.id.I_progressbar);
        a_progressbar = findViewById(R.id.A_progressbar);
        s_progressbar = findViewById(R.id.S_progressbar);
        e_progressbar = findViewById(R.id.E_progressbar);
        c_progressbar = findViewById(R.id.C_progressbar);

        r_progress = findViewById(R.id.R_progress);
        i_progress = findViewById(R.id.I_progress);
        a_progress = findViewById(R.id.A_progress);
        s_progress = findViewById(R.id.S_progress);
        e_progress = findViewById(R.id.E_progress);
        c_progress = findViewById(R.id.C_progress);

        /*
        r_description = findViewById(R.id.R_description);
        i_description = findViewById(R.id.I_description);
        a_description = findViewById(R.id.A_description);
        s_description = findViewById(R.id.S_description);
        e_description = findViewById(R.id.E_description);
        c_description = findViewById(R.id.C_description);
         */
    }
}