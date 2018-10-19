package com.example.clientforspringsecuritypetshop.service;

import com.example.clientforspringsecuritypetshop.model.Login;
import com.example.clientforspringsecuritypetshop.model.Pet;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserClient {
    @POST("auth")
    public Call<ResponseBody> login(@Body Login login);

    @GET("api")
//    public Call<List<Pet>> petList(@Header("Token") String token);
    public Call<List<Pet>> petList();
}
