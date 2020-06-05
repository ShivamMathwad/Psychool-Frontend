package com.aptitude.shivam.aptitude.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("ocean_result")
    @Expose
    private OceanModel oceanResult;
    @SerializedName("aptitude_result")
    @Expose
    private AptitudeModel aptitudeResult;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public OceanModel getOceanResult() {
        return oceanResult;
    }

    public void setOceanResult(OceanModel oceanResult) {
        this.oceanResult = oceanResult;
    }

    public AptitudeModel getAptitudeResult() {
        return aptitudeResult;
    }

    public void setAptitudeResult(AptitudeModel aptitudeResult) {
        this.aptitudeResult = aptitudeResult;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", oceanResult=" + oceanResult +
                ", aptitudeResult=" + aptitudeResult +
                '}';
    }
}
