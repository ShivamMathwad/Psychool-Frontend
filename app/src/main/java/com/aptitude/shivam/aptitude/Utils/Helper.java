package com.aptitude.shivam.aptitude.Utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class Helper {

    public static ArrayList calcPersonality(List<Integer> arr){
        ArrayList<Float> result = new ArrayList<>();
        float extraversion, agreeableness, conscientiousness, neuroticism, openness;
        extraversion=agreeableness=conscientiousness=neuroticism=openness=0;

        //arr = Arrays.asList(2,2,3,4,5,3,4,1,4,2,3,1,4,4,4,4,5,1,3,5,4,2,3,3,4,3,4,1,2,2,4,4,5,1,4,3,2,3,1,2,4,4,3,1,4,4,4,4,3,3);

        extraversion      = 20+arr.get(0)-arr.get(5)+arr.get(10)-arr.get(15)+arr.get(20)-arr.get(25)+arr.get(30)-arr.get(35)+arr.get(40)-arr.get(45);
        agreeableness     = 14-arr.get(1)+arr.get(6)-arr.get(11)+arr.get(16)-arr.get(21)+arr.get(26)-arr.get(31)+arr.get(36)+arr.get(41)+arr.get(46);
        conscientiousness = 14+arr.get(2)-arr.get(7)+arr.get(12)-arr.get(17)+arr.get(22)-arr.get(27)+arr.get(32)-arr.get(37)+arr.get(42)+arr.get(47);
        neuroticism       = 38-arr.get(3)+arr.get(8)-arr.get(13)+arr.get(18)-arr.get(23)-arr.get(28)-arr.get(33)-arr.get(38)-arr.get(43)-arr.get(48);
        openness          =  8+arr.get(4)-arr.get(9)+arr.get(14)-arr.get(19)+arr.get(24)-arr.get(29)+arr.get(34)+arr.get(39)+arr.get(44)+arr.get(49);

        extraversion = (extraversion/40)*100;
        agreeableness = (agreeableness/40)*100;
        conscientiousness = (conscientiousness/40)*100;
        neuroticism = (neuroticism/40)*100;
        openness = (openness/40)*100;

        result.add(openness);
        result.add(conscientiousness);
        result.add(extraversion);
        result.add(agreeableness);
        result.add(neuroticism);

        return result;
    }

}
