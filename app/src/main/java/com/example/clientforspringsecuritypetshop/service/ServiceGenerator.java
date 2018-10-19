package com.example.clientforspringsecuritypetshop.service;

import com.example.clientforspringsecuritypetshop.interceptors.HeaderInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static OkHttpClient.Builder sHttpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://petshop-server.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create());
//    Retrofit retrofit = builder.build();


    public static <S> S createService(Class<S> serviceClass) {

//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        sHttpClient.addInterceptor(new HeaderInterceptor());
//        sHttpClient.addInterceptor(logging);

        Retrofit retrofit = builder
                .client(sHttpClient.build())
                .build();
        return  retrofit.create(serviceClass);
    }
}
