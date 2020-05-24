package com.aptitude.shivam.aptitude.Network;

import com.aptitude.shivam.aptitude.Model.QuizModel;
import com.mongodb.connection.Server;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public  class NetworkClient {

    public interface ServerCommunicator{

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/signup")
        Call<QuizModel> sendQuizResult(@Body QuizModel model6);


    }

    public static ServerCommunicator getCommunicator(String url){
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

        return retrofit.create(ServerCommunicator.class);
    }


}
