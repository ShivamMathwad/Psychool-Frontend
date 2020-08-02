package com.aptitude.shivam.aptitude.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RaisecModel {

    @SerializedName("r_result")
    @Expose
    private Integer rResult;
    @SerializedName("a_result")
    @Expose
    private Integer aResult;
    @SerializedName("i_result")
    @Expose
    private Integer iResult;
    @SerializedName("s_result")
    @Expose
    private Integer sResult;
    @SerializedName("e_result")
    @Expose
    private Integer eResult;
    @SerializedName("c_result")
    @Expose
    private Integer cResult;

    public Integer getrResult() {
        return rResult;
    }

    public void setrResult(Integer rResult) {
        this.rResult = rResult;
    }

    public Integer getaResult() {
        return aResult;
    }

    public void setaResult(Integer aResult) {
        this.aResult = aResult;
    }

    public Integer getiResult() {
        return iResult;
    }

    public void setiResult(Integer iResult) {
        this.iResult = iResult;
    }

    public Integer getsResult() {
        return sResult;
    }

    public void setsResult(Integer sResult) {
        this.sResult = sResult;
    }

    public Integer geteResult() {
        return eResult;
    }

    public void seteResult(Integer eResult) {
        this.eResult = eResult;
    }

    public Integer getcResult() {
        return cResult;
    }

    public void setcResult(Integer cResult) {
        this.cResult = cResult;
    }
}
