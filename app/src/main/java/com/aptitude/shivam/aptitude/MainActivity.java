package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aptitude.shivam.aptitude.Utils.Constants;
import com.aptitude.shivam.aptitude.Utils.Helper;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView username,nav_header_username;
    CardView aptitude_card,psychometric_card,result_card,profile_card;
    DrawerLayout mDrawerlayout;
    ActionBarDrawerToggle mToggle;
    MenuItem logout;
    Toolbar toolbar;
    ImageView hamburger_menu;
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        nav_header_username = headerView.findViewById(R.id.nav_header_username);
        nav_header_username.setText(Constants.Username);

        appBarLayout = findViewById(R.id.appbarLayout);
        hamburger_menu = appBarLayout.findViewById(R.id.hamburger_menu);
        hamburger_menu.setOnClickListener(this);

        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void init(){
        username = findViewById(R.id.username);
        aptitude_card = findViewById(R.id.aptitude_card);
        psychometric_card = findViewById(R.id.psychometric_card);
        result_card = findViewById(R.id.result_card);
        profile_card = findViewById(R.id.profile_card);
        mDrawerlayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        //hamburger_menu = findViewById(R.id.hamburger_menu);
        logout = findViewById(R.id.logout);

        username.setText(Constants.Username);

        aptitude_card.setOnClickListener(this);
        psychometric_card.setOnClickListener(this);
        result_card.setOnClickListener(this);
        profile_card.setOnClickListener(this);
        //hamburger_menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aptitude_card:
                startActivity(new Intent(MainActivity.this, AptitudeMain.class));
                break;

            case R.id.psychometric_card:
                startActivity(new Intent(MainActivity.this, Quiz_layout.class));
                break;

            case R.id.result_card:
                startActivity(new Intent(MainActivity.this, Results.class));
                break;

            case R.id.profile_card:
                //startActivity(new Intent(MainActivity.this,Quiz_layout.class));
                break;

            case R.id.hamburger_menu:
                Log.d("TAG","In switch case");
                if(!mDrawerlayout.isDrawerOpen(GravityCompat.START))
                    mDrawerlayout.openDrawer(Gravity.LEFT);
                else
                    mDrawerlayout.closeDrawer(GravityCompat.END);
                //mDrawerlayout.openDrawer(GravityCompat.START);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                startActivity(new Intent(MainActivity.this,Login.class));
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
