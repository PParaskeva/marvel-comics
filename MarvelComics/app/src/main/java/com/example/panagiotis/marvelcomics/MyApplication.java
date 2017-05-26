package com.example.panagiotis.marvelcomics;

import android.app.Application;

import io.realm.Realm;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }

}
