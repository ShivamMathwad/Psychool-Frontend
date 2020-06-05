package com.aptitude.shivam.aptitude.Network;

import com.aptitude.shivam.aptitude.Model.OceanModel;
import com.aptitude.shivam.aptitude.Model.OceanQuestionModel;
import com.aptitude.shivam.aptitude.Model.QuizModel;
import com.aptitude.shivam.aptitude.Model.StatusModel;
import com.aptitude.shivam.aptitude.Model.UserModel;

import java.util.List;
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

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/signup")
        Call<StatusModel> signup(@Body UserModel model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/login")
        Call<StatusModel> login(@Body UserModel model);

        @GET("/getQuestions")
        Call<List<OceanQuestionModel>> getOceanQuestions();

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/storeOceanResult")
        Call<StatusModel> storeOceanResult(@Body UserModel model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/getOceanResult")
        Call<OceanModel> getOceanResult(@Body UserModel model);
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
