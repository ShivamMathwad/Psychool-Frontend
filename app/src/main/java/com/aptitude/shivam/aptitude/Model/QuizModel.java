package com.aptitude.shivam.aptitude.Model;

import java.util.ArrayList;
import java.util.List;

public class QuizModel {
    public List<String> questionsList = new ArrayList<>();

    public static String option1 = "Disagree";
    public static String option2 = "Slightly Disagree";
    public static String option3 = "Neutral";
    public static String option4 = "Slightly Agree";
    public static String option5 = "Agree";

    public List<OptionsModel> optionsList = new ArrayList<>();


    public void populateQuestionsList(List<String> questionsList){
        this.questionsList = questionsList;
    }

    public void populateOptionsList(List<OptionsModel> optionsList){
        this.optionsList = optionsList;
    }
}
