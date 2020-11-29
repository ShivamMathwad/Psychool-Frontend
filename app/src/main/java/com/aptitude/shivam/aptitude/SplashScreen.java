package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import static java.lang.Thread.sleep;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);

        if (sp.getBoolean("logged", false)) {
            finish();
            if (sp.getString("user_type", "").equals("School/High School")) {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
            } else {
                startActivity(new Intent(SplashScreen.this, GradMainActivity.class));
            }
        } else {
            startThread();
        }
    }

    public void startThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(2500);
                    startActivity(new Intent(SplashScreen.this, Login.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
