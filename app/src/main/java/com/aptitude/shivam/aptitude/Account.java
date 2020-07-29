package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aptitude.shivam.aptitude.Model.StatusModel;
import com.aptitude.shivam.aptitude.Model.UserModel;
import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Constants;
import com.aptitude.shivam.aptitude.Utils.Helper;
import com.aptitude.shivam.aptitude.Utils.PasswordUtils;

import static android.util.Log.d;

public class Account extends AppCompatActivity implements View.OnClickListener {

    String str_password, str_confirmPassword;
    TextView username, email;
    EditText new_password, confirm_password;
    Button submit;
    Dialog loadingDialog;
    NetworkClient.ServerCommunicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        loadingDialog = Helper.createDialog(this, R.layout.loading_dialog, "Saving your new password");

        init();
    }

    public void init(){
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        new_password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        submit = findViewById(R.id.submit);

        username.setText(Constants.Username);
        email.setText(Constants.Email);

        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
                str_password = new_password.getText().toString();
                str_confirmPassword = confirm_password.getText().toString();

                if(validate()){
                    String secure_password = PasswordUtils.generateSecurePassword(str_password, Constants.salt);

                    loadingDialog.show();
                    UserModel userModel = new UserModel();
                    userModel.setUsername(Constants.Username);
                    userModel.setPassword(secure_password);

                    communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);
                    Call<StatusModel> call = communicator.changePassword(userModel);
                    call.enqueue(new PasswordChangeHandler());
                }
                break;
        }
    }

    public boolean validate() {
        boolean flag = true;

        if (str_password.length() < 2) {
            new_password.setError("Password must be more than 1 character");
            flag = false;
        }
        if (!str_confirmPassword.equals(str_password)) {
            confirm_password.setError("Passwords don't match");
            flag = false;
        }
        return flag;
    }

    private class PasswordChangeHandler implements Callback<StatusModel> {
        @Override
        public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
            StatusModel statusModel = response.body();
            if (statusModel.getStatus().equals("Success")) {
                Toast.makeText(Account.this, "Changed your password successfully", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Account.this, "Error!!", Toast.LENGTH_LONG).show();
            }
            loadingDialog.dismiss();
        }
        @Override
        public void onFailure(Call<StatusModel> call, Throwable t) {
            d("TAG", "Error :" + t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(Account.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

}