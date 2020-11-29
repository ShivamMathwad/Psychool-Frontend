package com.aptitude.shivam.aptitude.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AptitudeQuestionModel {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("question")
    @Expose
    private String question;

    @SerializedName("optionA")
    @Expose
    private String optionA;

    @SerializedName("optionB")
    @Expose
    private String optionB;

    @SerializedName("optionC")
    @Expose
    private String optionC;

    @SerializedName("optionD")
    @Expose
    private String optionD;

    @SerializedName("correctOption")
    @Expose
    private String correctOption;

    @SerializedName("type")
    @Expose
    private String type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
