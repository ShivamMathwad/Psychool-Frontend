package com.aptitude.shivam.aptitude.Network;

import com.aptitude.shivam.aptitude.Model.OceanQuestionModel;
import com.aptitude.shivam.aptitude.Model.QuizModel;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public  class NetworkClient {

    public interface ServerCommunicator{

        @POST("/signup")
        Call<QuizModel> sendQuizResult(@Body QuizModel model);

        @GET("/getQuestions")
        Call<OceanQuestionModel> getOceanQuestions();

    }

    public static ServerCommunicator getCommunicator(String url){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(okHttpClient)
                            .build();

        return retrofit.create(ServerCommunicator.class);
    }


}
