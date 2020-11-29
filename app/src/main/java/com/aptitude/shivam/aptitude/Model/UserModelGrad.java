package com.aptitude.shivam.aptitude.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserModelGrad {

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
    @SerializedName("recommendation")
    @Expose
    private List<String> recommendation;
    @SerializedName("ocean_result")
    @Expose
    private OceanModel oceanResult;
    @SerializedName("raisec_result")
    @Expose
    private RaisecModel raisecResult;
    @SerializedName("medical")
    @Expose
    private Integer medical;
    @SerializedName("management")
    @Expose
    private Integer management;
    @SerializedName("political")
    @Expose
    private Integer political;
    @SerializedName("computer")
    @Expose
    private Integer computer;
    @SerializedName("mechanical")
    @Expose
    private Integer mechanical;
    @SerializedName("aerospace")
    @Expose
    private Integer aerospace;

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

    public Integer getMedical() {
        return medical;
    }

    public void setMedical(Integer medical) {
        this.medical = medical;
    }

    public Integer getManagement() {
        return management;
    }

    public void setManagement(Integer management) {
        this.management = management;
    }

    public Integer getPolitical() {
        return political;
    }

    public void setPolitical(Integer political) {
        this.political = political;
    }

    public Integer getComputer() {
        return computer;
    }

    public void setComputer(Integer computer) {
        this.computer = computer;
    }

    public Integer getMechanical() {
        return mechanical;
    }

    public void setMechanical(Integer mechanical) {
        this.mechanical = mechanical;
    }

    public Integer getAerospace() {
        return aerospace;
    }

    public void setAerospace(Integer aerospace) {
        this.aerospace = aerospace;
    }

    public RaisecModel getRaisecResult() {
        return raisecResult;
    }

    public void setRaisecResult(RaisecModel raisecResult) {
        this.raisecResult = raisecResult;
    }

    public List<String> getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(List<String> recommendation) {
        this.recommendation = recommendation;
    }
}
