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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aptitude.shivam.aptitude.Model.StatusModel;
import com.aptitude.shivam.aptitude.Model.UserModel;
import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Constants;
import com.aptitude.shivam.aptitude.Utils.Helper;
import com.aptitude.shivam.aptitude.Utils.PasswordUtils;

import static android.util.Log.d;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    EditText username, email, password, confirm_password;
    Button submit;
    Spinner spinner;
    String str_username, str_email, str_password, str_confirmPassword, user_type;
    Dialog loadingDialog;
    NetworkClient.ServerCommunicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        loadingDialog = Helper.createDialog(this, R.layout.loading_dialog, "Creating your account");

        init();

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Signup.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.user_type));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);
    }

    public void init(){
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        submit = findViewById(R.id.submit);
        spinner = findViewById(R.id.spinner);

        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
                user_type = spinner.getSelectedItem().toString();
                str_username = username.getText().toString();
                str_email = email.getText().toString();
                str_password = password.getText().toString();
                str_confirmPassword = confirm_password.getText().toString();

                if(validate()){
                    String secure_password = PasswordUtils.generateSecurePassword(str_password, Constants.salt);

                    loadingDialog.show();
                    UserModel userModel = new UserModel();
                    userModel.setUsername(str_username);
                    userModel.setEmail(str_email);
                    userModel.setPassword(secure_password);
                    userModel.setUserType(user_type);

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
        if (!str_confirmPassword.equals(str_password)) {
            confirm_password.setError("Passwords don't match");
            flag = false;
        }
        if (str_email.indexOf("@") <= 0 || str_email.indexOf(".") <= 0 || str_email.length() < 9) {
            email.setError("Please enter valid email");
            flag = false;
        }
        return flag;
    }

    private class OnSignupHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();

            if(statusModel.getStatus().equals("Success")){
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
                Toast.makeText(Signup.this, "Your account is created successfully!!", Toast.LENGTH_SHORT).show();
                finish();
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
