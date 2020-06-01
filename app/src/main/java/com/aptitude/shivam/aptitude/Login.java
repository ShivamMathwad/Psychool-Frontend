package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener{

    EditText username,password;
    Button login;
    TextView register;
    String str_username, str_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    public void init(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                str_username = username.getText().toString();
                str_password = password.getText().toString();

                /*
                if (db.readfromDB(name2, password2).size() == 1)
                {
                    Toast.makeText(this, "Welcome "+name2, Toast.LENGTH_SHORT).show();
                    Helper.savetoSharedPref(Login.this,"Login","username",name2);
                    startActivity(new Intent(Login.this,MainActivity.class));
                }
                else
                    Toast.makeText(this, "Please enter valid username and password", Toast.LENGTH_SHORT).show();
                 */
                break;

            case R.id.register:
                startActivity(new Intent(Login.this, Signup.class));
                break;
        }
    }

}
