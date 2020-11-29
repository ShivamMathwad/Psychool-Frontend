package com.aptitude.shivam.aptitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aptitude.shivam.aptitude.Model.StatusModel;
import com.aptitude.shivam.aptitude.Model.TestStatusModel;
import com.aptitude.shivam.aptitude.Model.UserModel;
import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Constants;
import com.aptitude.shivam.aptitude.Utils.Helper;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.util.Log.d;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    TextView username,nav_header_username;
    CardView aptitude_card,personality_card,result_card,job_card;
    DrawerLayout drawerlayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    Dialog loadingDialog;
    NetworkClient.ServerCommunicator communicator;
    SharedPreferences sp;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingDialog = Helper.createDialog(this, R.layout.loading_dialog, "Checking your test status");

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
        job_card = findViewById(R.id.job_card);
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
        job_card.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aptitude_card:
                startActivity(new Intent(MainActivity.this, AptitudeMain.class));
                break;

            case R.id.personality_card:
                startActivity(new Intent(MainActivity.this, PersonalityMain.class));
                break;

            case R.id.result_card:
                startActivity(new Intent(MainActivity.this, Results.class));
                break;

            case R.id.job_card:
                loadingDialog.show();

                UserModel userModel = new UserModel();
                userModel.setUsername(Constants.Username);
                communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);
                Call<TestStatusModel> call = communicator.checkTestStatus(userModel);
                call.enqueue(new TestStatusHandler());
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.logout:
                sp.edit().putBoolean("logged", false).apply();
                finish();
                startActivity(new Intent(MainActivity.this,Login.class));
                break;

            case R.id.account:
                startActivity(new Intent(MainActivity.this, Account.class));
                break;

            case R.id.about:
                startActivity(new Intent(MainActivity.this,About.class));
                break;
        }
        return false;
    }

    private class TestStatusHandler implements Callback<TestStatusModel> {
        @Override
        public void onResponse(Call<TestStatusModel> call, Response<TestStatusModel> response) {
            TestStatusModel testStatusModel = response.body();

            if(testStatusModel.getStatus().equals("Success")){
                Intent intent = new Intent(MainActivity.this,TestChecklist.class);
                intent.putExtra("personality",testStatusModel.getPersonality());
                intent.putExtra("numerical",testStatusModel.getNumerical());
                intent.putExtra("perceptual",testStatusModel.getPerceptual());
                intent.putExtra("verbal",testStatusModel.getVerbal());
                intent.putExtra("abstract",testStatusModel.getAbstractApti());
                intent.putExtra("spatial",testStatusModel.getSpatial());
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Error!!", Toast.LENGTH_LONG).show();
            }
            loadingDialog.dismiss();
        }
        @Override
        public void onFailure(Call<TestStatusModel> call, Throwable t) {
            d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(MainActivity.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

}
