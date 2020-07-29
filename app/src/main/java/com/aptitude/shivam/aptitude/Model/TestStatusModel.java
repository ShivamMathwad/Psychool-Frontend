package com.aptitude.shivam.aptitude.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestStatusModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("personality")
    @Expose
    private Boolean personality;
    @SerializedName("numerical")
    @Expose
    private Boolean numerical;
    @SerializedName("perceptual")
    @Expose
    private Boolean perceptual;
    @SerializedName("verbal")
    @Expose
    private Boolean verbal;
    @SerializedName("abstractApti")
    @Expose
    private Boolean abstractApti;
    @SerializedName("spatial")
    @Expose
    private Boolean spatial;

    public Boolean getPersonality() {
        return personality;
    }

    public void setPersonality(Boolean personality) {
        this.personality = personality;
    }

    public Boolean getNumerical() {
        return numerical;
    }

    public void setNumerical(Boolean numerical) {
        this.numerical = numerical;
    }

    public Boolean getPerceptual() {
        return perceptual;
    }

    public void setPerceptual(Boolean perceptual) {
        this.perceptual = perceptual;
    }

    public Boolean getVerbal() {
        return verbal;
    }

    public void setVerbal(Boolean verbal) {
        this.verbal = verbal;
    }

    public Boolean getAbstractApti() {
        return abstractApti;
    }

    public void setAbstractApti(Boolean abstractApti) {
        this.abstractApti = abstractApti;
    }

    public Boolean getSpatial() {
        return spatial;
    }

    public void setSpatial(Boolean spatial) {
        this.spatial = spatial;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
