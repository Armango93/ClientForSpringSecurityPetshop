package com.example.clientforspringsecuritypetshop.managers;

import android.content.SharedPreferences;

import com.example.clientforspringsecuritypetshop.App;

public class PreferencesManager {
    private SharedPreferences mSharedPreferences;

    public PreferencesManager() {
        mSharedPreferences = App.getmSharedPreferences();
    }


    public void saveToken(String token){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public String getToken(){
        return mSharedPreferences.getString("token", "null");
    }
}
