package com.aptitude.shivam.aptitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.aptitude.shivam.aptitude.Utils.Constants;
import com.google.android.material.navigation.NavigationView;

public class GradMainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    TextView username,nav_header_username;
    CardView aptitude_card,personality_card,result_card;
    DrawerLayout drawerlayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grad_main);

        sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        Constants.Username = sp.getString("username","");
        Constants.user_type = sp.getString("user_type","");
        Constants.Email = sp.getString("email","");

        init();

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_hamburger_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.open, R.string.close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void init(){
        username = findViewById(R.id.username);
        aptitude_card = findViewById(R.id.aptitude_card);
        personality_card = findViewById(R.id.personality_card);
        result_card = findViewById(R.id.result_card);
        drawerlayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);

        navigationView = findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        nav_header_username = headerView.findViewById(R.id.nav_header_username);

        nav_header_username.setText(Constants.Username);
        username.setText(Constants.Username);

        navigationView.setNavigationItemSelectedListener(this);
        aptitude_card.setOnClickListener(this);
        personality_card.setOnClickListener(this);
        result_card.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aptitude_card:
                startActivity(new Intent(this, GradAptitudeMain.class));
                break;

            case R.id.personality_card:
                startActivity(new Intent(this, PersonalityMain.class));
                break;

            case R.id.result_card:
                startActivity(new Intent(this, Results.class));
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.logout:
                finish();
                startActivity(new Intent(this,Login.class));
                break;

            case R.id.account:
                startActivity(new Intent(GradMainActivity.this, Account.class));
                break;

            case R.id.about:
                startActivity(new Intent(GradMainActivity.this,About.class));
                break;
        }
        return false;
    }

}