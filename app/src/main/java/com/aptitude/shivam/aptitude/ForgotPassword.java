package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aptitude.shivam.aptitude.Model.ForgotPasswordModel;
import com.aptitude.shivam.aptitude.Model.StatusModel;
import com.aptitude.shivam.aptitude.Model.UserModel;
import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Constants;
import com.aptitude.shivam.aptitude.Utils.Helper;
import com.aptitude.shivam.aptitude.Utils.PasswordUtils;

import static android.util.Log.d;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {

    EditText username, email;
    TextView text;
    Button submit;
    String str_username, str_email, password, secure_password;
    Dialog loadingDialog;
    NetworkClient.ServerCommunicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        loadingDialog = Helper.createDialog(this, R.layout.loading_dialog, "Checking your input account credentials");

        init();

        text.setVisibility(View.INVISIBLE);
    }

    public void init(){
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        text = findViewById(R.id.text);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.submit:
                str_username = username.getText().toString();
                str_email = email.getText().toString();

                if(validate()){
                    password = Helper.createPassword();
                    secure_password = PasswordUtils.generateSecurePassword(password, Constants.salt);
                    loadingDialog.show();

                    ForgotPasswordModel model = new ForgotPasswordModel();
                    model.setUsername(str_username);
                    model.setEmail(str_email);
                    model.setPassword(password);
                    model.setSecurePassword(secure_password);

                    communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);
                    Call<StatusModel> call = communicator.forgotPassword(model);
                    call.enqueue(new ForgotPasswordHandler());
                }
                break;
        }
    }

    public boolean validate() {
        boolean flag = true;

        if (str_username.length() < 2) {
            username.setError("Username is never less than 2 characters");
            flag = false;
        }
        if (str_email.indexOf("@") <= 0 || str_email.indexOf(".") <= 0 || str_email.length() < 9) {
            email.setError("Please enter valid email");
            flag = false;
        }
        return flag;
    }

    private class ForgotPasswordHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();

            if (statusModel.getStatus().equals("Success")) {
                text.setVisibility(View.VISIBLE);
            } else if (statusModel.getStatus().equals("Invalid details")) {
                Toast.makeText(ForgotPassword.this, "Account details are invalid!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(ForgotPassword.this, "Error!!", Toast.LENGTH_LONG).show();
            }
            loadingDialog.dismiss();
        }
        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(ForgotPassword.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

}