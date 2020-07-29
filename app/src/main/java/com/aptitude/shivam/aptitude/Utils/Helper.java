package com.aptitude.shivam.aptitude.Utils;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.aptitude.shivam.aptitude.Model.ImageModel;
import com.aptitude.shivam.aptitude.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.aptitude.shivam.aptitude.Utils.Constants.allQuestionsAndOptions;
import static java.lang.Math.round;


public class Helper {

    public static Dialog createDialog(Context context, int layoutFile, String message){
        View view = LayoutInflater.from(context).inflate(layoutFile,null,false);
        TextView loadingText = view.findViewById(R.id.loadingText);
        loadingText.setText(message);

        Dialog dialog=new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    public static String createPassword(){
        int number = (int) Math.floor(Math.random() * (9999 - 1000) + 1000);

        String AlphaNumericString = "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(2);
        for (int i=0; i<2; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index)); // add Character one by one in end of sb
        }

        return String.valueOf(number) + sb.toString();
    }

    public static ArrayList calcPersonality(ArrayList<Integer> arr){
        ArrayList<Integer> result = new ArrayList<>();
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

        result.add(round(openness));
        result.add(round(conscientiousness));
        result.add(round(extraversion));
        result.add(round(agreeableness));
        result.add(round(neuroticism));

        return result;
    }

    public static Integer calcAptitudeScore(Map<Integer,String> map){
        float score = 0;
        int result;

        for(int i=0; i<map.size(); i++){
            if(allQuestionsAndOptions.get(i).getCorrectOption().equals(map.get(i)) ){
                score += 1;
            } else {
                score += 0;
            }
        }
        score = (score/map.size())*100;
        result = Math.round(score);

        return result;
    }

    public static Integer calc_ARandSA_score(Map<Integer,String> map, List<ImageModel> list){
        float score = 0;
        int result;

        for(int i=0; i<map.size(); i++){
            if(list.get(i).getCorrectOption().equals(map.get(i)) ){
                score += 1;
            } else {
                score += 0;
            }
        }
        score = (score/map.size())*100;
        result = Math.round(score);

        return result;
    }

    public static List<ImageModel> abstractModelList(){
        String question, option;
        List<ImageModel> modelList = new ArrayList<>();
        String[] correctOptionList = Constants.abstractCorrectOptionList;

        for(int i=1;i<=30;i++){
            ImageModel object = new ImageModel();
            question = "ar_q"+i;
            option = "ar_op"+i;
            object.setId(i);
            object.setQuestion(question);
            object.setOptions(option);
            object.setCorrectOption(correctOptionList[i-1]);
            modelList.add(object);
        }
        return modelList;
    }

    public static List<ImageModel> spatialModelList(){
        String question, option;
        List<ImageModel> modelList = new ArrayList<>();
        String[] correctOptionList = Constants.spatialCorrectOptionList;

        for(int i=1;i<=30;i++){
            ImageModel object = new ImageModel();
            question = "sa_q"+i;
            option = "sa_op"+i;
            object.setId(i);
            object.setQuestion(question);
            object.setOptions(option);
            object.setCorrectOption(correctOptionList[i-1]);
            modelList.add(object);
        }
        return modelList;
    }

}
