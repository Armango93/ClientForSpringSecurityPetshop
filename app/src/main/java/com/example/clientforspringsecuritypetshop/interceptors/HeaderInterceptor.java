package com.example.clientforspringsecuritypetshop.interceptors;

import com.example.clientforspringsecuritypetshop.managers.DataManager;
import com.example.clientforspringsecuritypetshop.managers.PreferencesManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        PreferencesManager preferencesManager = DataManager.getInstance().getmPreferencesManager();
        Request request = chain.request();
        Request.Builder builder = request.newBuilder().header("Token", preferencesManager.getToken());
        Request finalRequest = builder.build();
        return chain.proceed(finalRequest);
    }
}
