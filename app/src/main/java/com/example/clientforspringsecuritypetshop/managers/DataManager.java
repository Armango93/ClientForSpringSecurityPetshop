package com.example.clientforspringsecuritypetshop.managers;

import android.content.Context;

import com.example.clientforspringsecuritypetshop.App;
import com.example.clientforspringsecuritypetshop.model.DaoSession;
import com.example.clientforspringsecuritypetshop.service.ServiceGenerator;
import com.example.clientforspringsecuritypetshop.service.UserClient;

public class DataManager {
    private static DataManager INSTANCE = null;

    private Context mContext;
    private PreferencesManager mPreferencesManager;
    private UserClient mUserClient;
    private DaoSession daoSession;

    private DataManager() {
        mPreferencesManager = new PreferencesManager();
        mContext = App.getmContext();
        mUserClient = ServiceGenerator.createService(UserClient.class);
        daoSession = App.getDaoSession();
    }

    public static DataManager getInstance(){
        if(INSTANCE == null){
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    public Context getmContext() {
        return mContext;
    }

    public PreferencesManager getmPreferencesManager() {
        return mPreferencesManager;
    }

    public UserClient getmUserClient() {
        return mUserClient;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
