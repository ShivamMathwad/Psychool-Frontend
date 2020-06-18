package com.aptitude.shivam.aptitude.Utils;

import com.aptitude.shivam.aptitude.Model.AptitudeQuestionModel;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static final String SERVER_URL = "https://psychool-backend.herokuapp.com/";
    public static final String NU_instructions = "The test consists of 30 arithmetical problems, some of which are numerical and some are word problems. \nEach problem is followed by four options. \nChoose the correct option and do calculations if any, on a Rough Sheet.\nThe following two questions have been solved for you as examples.\n\nExample X -\n   1373+2841+2254=?\n    A. 6368\n    B. 5468\n    C. 6468\n    D. 6458\nCorrect answer is C.\n\nExample Y -\n   A bucket has a capacity of 11 litres. How many buckets of water are required to fill a tank having capacity of 121 litres?\n   A. 10\n   B. 11\n   C. 15\n   D. 13\nCorrect answer is B.\n\n You will have 10 minutes for this test. Work quickly and solve as many problems as you can.";
    public static List<AptitudeQuestionModel> allQuestionsAndOptions = null;
    public static String Username = "";


}
