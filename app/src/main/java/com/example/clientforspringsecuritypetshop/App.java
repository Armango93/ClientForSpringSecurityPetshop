package com.example.clientforspringsecuritypetshop;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.clientforspringsecuritypetshop.managers.PreferencesManager;
import com.example.clientforspringsecuritypetshop.model.DaoMaster;
import com.example.clientforspringsecuritypetshop.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class App extends Application {
    private static SharedPreferences mSharedPreferences;
    private static Context mContext;
    private static DaoSession daoSession;

    public static Context getmContext() {
        return mContext;
    }

    public static SharedPreferences getmSharedPreferences() {
        return mSharedPreferences;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "pet_db");
        Database database = helper.getWritableDb();
        daoSession = new DaoMaster(database).newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
