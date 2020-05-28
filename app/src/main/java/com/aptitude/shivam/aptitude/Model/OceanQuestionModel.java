
package com.aptitude.shivam.aptitude.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class OceanQuestionModel {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("question")
    @Expose
    private String question;

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("reverse")
    @Expose
    private String reverse;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReverse() {
        return reverse;
    }

    public void setReverse(String reverse) {
        this.reverse = reverse;
    }

    @Override
    public String toString() {
        return "OceanQuestionModel{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", type='" + type + '\'' +
                ", reverse='" + reverse + '\'' +
                '}';
    }
}
