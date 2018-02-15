package com.lence.startpattern.api;

import android.app.Application;

import com.lence.startpattern.SingletonStorage;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App extends Application {
    private static Api sApi;
    private Retrofit retrofit;
    private SingletonStorage mStorage;

    @Override
    public void onCreate() {
        super.onCreate();
        SingletonStorage.initInstance();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://med.cg42392.tmweb.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sApi = retrofit.create(Api.class);
    }

    public static Api getApi() {
        return sApi;
    }
}
