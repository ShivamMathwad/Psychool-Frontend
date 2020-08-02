package com.aptitude.shivam.aptitude.Network;

import com.aptitude.shivam.aptitude.Model.AptitudeQuestionModel;
import com.aptitude.shivam.aptitude.Model.ForgotPasswordModel;
import com.aptitude.shivam.aptitude.Model.OceanModel;
import com.aptitude.shivam.aptitude.Model.OceanQuestionModel;
import com.aptitude.shivam.aptitude.Model.QuizModel;
import com.aptitude.shivam.aptitude.Model.RaisecModel;
import com.aptitude.shivam.aptitude.Model.RaisecQuestionModel;
import com.aptitude.shivam.aptitude.Model.StatusModel;
import com.aptitude.shivam.aptitude.Model.TestStatusModel;
import com.aptitude.shivam.aptitude.Model.UserModel;
import com.aptitude.shivam.aptitude.Model.UserModelGrad;

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

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/forgot_password")
        Call<StatusModel> forgotPassword(@Body ForgotPasswordModel model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/changePassword")
        Call<StatusModel> changePassword(@Body UserModel model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/checkTestStatus")
        Call<TestStatusModel> checkTestStatus(@Body UserModel model);

        @GET("/getQuestions")
        Call<List<OceanQuestionModel>> getOceanQuestions();

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/storeOceanResult")
        Call<StatusModel> storeOceanResult(@Body UserModel model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/getOceanResult")
        Call<OceanModel> getOceanResult(@Body UserModel model);

        @GET("/getInterestQuestions")
        Call<List<RaisecQuestionModel>> getInterestQuestions();

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/storeRaisecResult")
        Call<StatusModel> storeRaisecResult(@Body UserModel model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/getRaisecResult")
        Call<RaisecModel> getRaisecResult(@Body UserModel model);

        @GET("/getNAQuestions")
        Call<List<AptitudeQuestionModel>> getNAQuestions();

        @GET("/getPAQuestions")
        Call<List<AptitudeQuestionModel>> getPAQuestions();

        @GET("/getVRQuestions")
        Call<List<AptitudeQuestionModel>> getVRQuestions();

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/storeNAresult")
        Call<StatusModel> storeNAresult(@Body UserModel model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/getNAresult")
        Call<StatusModel> getNAresult(@Body UserModel model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/storePAresult")
        Call<StatusModel> storePAresult(@Body UserModel model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/getPAresult")
        Call<StatusModel> getPAresult(@Body UserModel model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/storeVRresult")
        Call<StatusModel> storeVRresult(@Body UserModel model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/getVRresult")
        Call<StatusModel> getVRresult(@Body UserModel model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/storeARresult")
        Call<StatusModel> storeARresult(@Body UserModel model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/getARresult")
        Call<StatusModel> getARresult(@Body UserModel model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/storeSAresult")
        Call<StatusModel> storeSAresult(@Body UserModel model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/getSAresult")
        Call<StatusModel> getSAresult(@Body UserModel model);

        @GET("/getMedicalQuestions")
        Call<List<AptitudeQuestionModel>> getMedicalQuestions();

        @GET("/getPoliticalQuestions")
        Call<List<AptitudeQuestionModel>> getPoliticalQuestions();

        @GET("/getManagementQuestions")
        Call<List<AptitudeQuestionModel>> getManagementQuestions();

        @GET("/getComputerQuestions")
        Call<List<AptitudeQuestionModel>> getComputerQuestions();

        @GET("/getMechanicalQuestions")
        Call<List<AptitudeQuestionModel>> getMechanicalQuestions();

        @GET("/getAerospaceQuestions")
        Call<List<AptitudeQuestionModel>> getAerospaceQuestions();

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/storeMedicalResult")
        Call<StatusModel> storeMedicalResult(@Body UserModelGrad model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/storePoliticalResult")
        Call<StatusModel> storePoliticalResult(@Body UserModelGrad model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/storeManagementResult")
        Call<StatusModel> storeManagementResult(@Body UserModelGrad model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/storeComputerResult")
        Call<StatusModel> storeComputerResult(@Body UserModelGrad model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/storeMechanicalResult")
        Call<StatusModel> storeMechanicalResult(@Body UserModelGrad model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/storeAerospaceResult")
        Call<StatusModel> storeAerospaceResult(@Body UserModelGrad model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/getMedicalResult")
        Call<StatusModel> getMedicalResult(@Body UserModelGrad model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/getPoliticalResult")
        Call<StatusModel> getPoliticalResult(@Body UserModelGrad model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/getManagementResult")
        Call<StatusModel> getManagementResult(@Body UserModelGrad model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/getComputerResult")
        Call<StatusModel> getComputerResult(@Body UserModelGrad model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/getMechanicalResult")
        Call<StatusModel> getMechanicalResult(@Body UserModelGrad model);

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("/getAerospaceResult")
        Call<StatusModel> getAerospaceResult(@Body UserModelGrad model);
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
