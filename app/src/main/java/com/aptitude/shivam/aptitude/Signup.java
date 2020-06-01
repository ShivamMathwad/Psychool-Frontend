package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aptitude.shivam.aptitude.Model.StatusModel;
import com.aptitude.shivam.aptitude.Model.UserModel;
import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Constants;

import static android.util.Log.d;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    EditText username,password;
    Button submit;
    String str_username, str_password;
    Dialog loadingDialog;
    NetworkClient.ServerCommunicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Creating your account");
        builder.setMessage("Please Wait...");
        loadingDialog=builder.create();

        init();
    }

    public void init(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
                str_username = username.getText().toString();
                str_password = password.getText().toString();

                if(validate()){
                    loadingDialog.show();
                    UserModel userModel = new UserModel();
                    userModel.setUsername(str_username);
                    userModel.setPassword(str_password);
                    userModel.setAptitudeResult(null);
                    userModel.setOceanResult(null);

                    Log.d("TAG",userModel.getUsername());

                    communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);
                    Call<StatusModel> call = communicator.signup(userModel);
                    call.enqueue(new OnSignupHandler());
                }
                break;
        }
    }

    public boolean validate() {
        boolean flag = true;

        if (str_username.length() < 2) {
            username.setError("Username must be more than 1 character");
            flag = false;
        }
        if (str_password.length() < 2) {
            password.setError("Password must be more than 1 character");
            flag = false;
        }
        return flag;
    }

    private class OnSignupHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();

            if(statusModel.getStatus().equals("Success")){
                Constants.Username = str_username;
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
                Toast.makeText(Signup.this, "Your account is created successfully!!", Toast.LENGTH_SHORT).show();
            } else if(statusModel.getStatus().equals("Username already exists")){
                Toast.makeText(Signup.this, "Username already exists, use different name!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Signup.this, "Error!!", Toast.LENGTH_LONG).show();
            }
            loadingDialog.dismiss();
        }

        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(Signup.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }
}
