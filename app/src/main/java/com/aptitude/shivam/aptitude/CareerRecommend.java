package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aptitude.shivam.aptitude.Model.AllScoresFloatModel;
import com.aptitude.shivam.aptitude.Model.AllScoresModel;
import com.aptitude.shivam.aptitude.Model.CareerRecommendModel;
import com.aptitude.shivam.aptitude.Network.NetworkClient;
import com.aptitude.shivam.aptitude.Utils.Constants;
import com.aptitude.shivam.aptitude.Utils.Helper;

import java.util.ArrayList;
import java.util.List;

import static android.util.Log.d;

public class CareerRecommend extends AppCompatActivity {

    ListView recommendList;
    List<CareerRecommendModel> careerRecommendationList;
    List<String> careerNames;
    Dialog loadingDialog;
    NetworkClient.ServerCommunicator communicator;
    double o_result, c_result, e_result, a_result, n_result, numerical, perceptual, verbal, abstractApti, spatial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_recommend);

        loadingDialog = Helper.createDialog(this, R.layout.loading_dialog, "Generating your recommendation. Please Wait! This may take a while !");
        loadingDialog.show();

        recommendList = findViewById(R.id.recommendList);

        AllScoresFloatModel model = new AllScoresFloatModel();
        model.setO_result(getIntent().getDoubleExtra("o_result",0.5));
        model.setC_result(getIntent().getDoubleExtra("c_result",0.5));
        model.setE_result(getIntent().getDoubleExtra("e_result",0.5));
        model.setA_result(getIntent().getDoubleExtra("a_result",0.5));
        model.setNumerical(getIntent().getDoubleExtra("numerical",0.5));
        model.setPerceptual(getIntent().getDoubleExtra("perceptual",0.5));
        model.setVerbal(getIntent().getDoubleExtra("verbal",0.5));
        model.setAbstractApti(getIntent().getDoubleExtra("abstract",0.5));
        model.setSpatial(getIntent().getDoubleExtra("spatial",0.5));
        model.setUsername(Constants.Username);
        model.setEmail(Constants.Email);

        communicator = NetworkClient.getCommunicator(Constants.SERVER_URL);
        Call<List<String>> call = communicator.getCareerRecommendation(model);
        call.enqueue(new CareerRecommendationHandler());


        //careerRecommendationList = new ArrayList<>();

//Put this in onSuccess of Retrofit

    }


    private class CareerRecommendationHandler implements Callback<List<String>>{
        @Override
        public void onResponse(Call<List<String>> call, Response<List<String>> response) {
            careerNames = response.body();
            RecommendListHandler recommendListHandler = new RecommendListHandler(careerNames);
            recommendList.setAdapter(recommendListHandler);
            loadingDialog.dismiss();
        }
        @Override
        public void onFailure(Call<List<String>> call, Throwable t) {
            d("TAG","Error :"+t.getMessage());
            loadingDialog.dismiss();
            Toast.makeText(CareerRecommend.this, "Error! No Internet..", Toast.LENGTH_LONG).show();
        }
    }

    private class RecommendListHandler extends BaseAdapter{
        //List<CareerRecommendModel> careerRecommendationList;
        List<String> careerNames;

        RecommendListHandler(List<String> careerNames){
            this.careerNames = careerNames;
        }

        @Override
        public int getCount() {
            return careerNames.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView  = getLayoutInflater().inflate(R.layout.career_recommendation_generic,parent,false);
            /*
            TextView name, descriptions, skills, universities;
            name = convertView.findViewById(R.id.name);
            descriptions = convertView.findViewById(R.id.desc);
            skills = convertView.findViewById(R.id.skills);
            universities = convertView.findViewById(R.id.unis);

            CareerRecommendModel model = careerRecommendationList.get(position);
            name.setText(model.getName());
            descriptions.setText(model.getDescription());
            skills.setText(model.getSkills());
            universities.setText(model.getUniversity());
            */
            TextView name;

            name = convertView.findViewById(R.id.name);

            name.setText(careerNames.get(position));
            return convertView;
        }
    }
}