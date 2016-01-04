package com.ladwa.aditya.devourfortwitter;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Aditya on 03-Jan-16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .name("myrealm.realm")
                .build();


        Realm.setDefaultConfiguration(config);
    }
}
