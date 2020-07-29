package com.aptitude.shivam.aptitude.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class UserModel {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("ocean_result")
    @Expose
    private OceanModel oceanResult;
    @SerializedName("numerical")
    @Expose
    private Integer numerical;
    @SerializedName("perceptual")
    @Expose
    private Integer perceptual;
    @SerializedName("abstractApti")
    @Expose
    private Integer abstractApti;
    @SerializedName("spatial")
    @Expose
    private Integer spatial;
    @SerializedName("verbal")
    @Expose
    private Integer verbal;

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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumerical() {
        return numerical;
    }

    public void setNumerical(Integer numerical) {
        this.numerical = numerical;
    }

    public Integer getPerceptual() {
        return perceptual;
    }

    public void setPerceptual(Integer perceptual) {
        this.perceptual = perceptual;
    }

    public Integer getAbstractApti() {
        return abstractApti;
    }

    public void setAbstractApti(Integer abstractApti) {
        this.abstractApti = abstractApti;
    }

    public Integer getSpatial() {
        return spatial;
    }

    public void setSpatial(Integer spatial) {
        this.spatial = spatial;
    }

    public Integer getVerbal() {
        return verbal;
    }

    public void setVerbal(Integer verbal) {
        this.verbal = verbal;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userType='" + userType + '\'' +
                ", oceanResult=" + oceanResult +
                ", numerical=" + numerical +
                ", perceptual=" + perceptual +
                ", abstractApti=" + abstractApti +
                ", spatial=" + spatial +
                ", verbal=" + verbal +
                '}';
    }
}
