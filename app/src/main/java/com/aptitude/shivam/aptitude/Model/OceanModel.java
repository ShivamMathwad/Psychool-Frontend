package com.aptitude.shivam.aptitude.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OceanModel {

    @SerializedName("o_result")
    @Expose
    private Integer oResult;
    @SerializedName("c_result")
    @Expose
    private Integer cResult;
    @SerializedName("e_result")
    @Expose
    private Integer eResult;
    @SerializedName("a_result")
    @Expose
    private Integer aResult;
    @SerializedName("n_result")
    @Expose
    private Integer nResult;

    public Integer getOResult() {
        return oResult;
    }

    public void setOResult(Integer oResult) {
        this.oResult = oResult;
    }

    public Integer getCResult() {
        return cResult;
    }

    public void setCResult(Integer cResult) {
        this.cResult = cResult;
    }

    public Integer getEResult() {
        return eResult;
    }

    public void setEResult(Integer eResult) {
        this.eResult = eResult;
    }

    public Integer getAResult() {
        return aResult;
    }

    public void setAResult(Integer aResult) {
        this.aResult = aResult;
    }

    public Integer getNResult() {
        return nResult;
    }

    public void setNResult(Integer nResult) {
        this.nResult = nResult;
    }

    @Override
    public String toString() {
        return "OceanModel{" +
                "oResult=" + oResult +
                ", cResult=" + cResult +
                ", eResult=" + eResult +
                ", aResult=" + aResult +
                ", nResult=" + nResult +
                '}';
    }
}
