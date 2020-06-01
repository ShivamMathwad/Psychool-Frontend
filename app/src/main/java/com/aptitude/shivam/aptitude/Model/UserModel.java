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
    private List<OceanModel> oceanResult = null;
    @SerializedName("aptitude_result")
    @Expose
    private List<Object> aptitudeResult = null;

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

    public List<OceanModel> getOceanResult() {
        return oceanResult;
    }

    public void setOceanResult(List<OceanModel> oceanResult) {
        this.oceanResult = oceanResult;
    }

    public List<Object> getAptitudeResult() {
        return aptitudeResult;
    }

    public void setAptitudeResult(List<Object> aptitudeResult) {
        this.aptitudeResult = aptitudeResult;
    }

}
