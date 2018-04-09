package com.lence.startpattern.ui.service;


import android.util.Log;

import com.lence.startpattern.api.App;
import com.lence.startpattern.model.SectionsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicePresenter {
    ServiceMvp mServiceMvp;

    public ServicePresenter(ServiceMvp serviceMvp) {
        mServiceMvp = serviceMvp;
    }

    public void loadSections() {
        Log.e("load", "load");
        App.getApi().getSections().enqueue(new Callback<List<SectionsModel>>() {
            @Override
            public void onResponse(Call<List<SectionsModel>> call, Response<List<SectionsModel>> response) {
                Log.e("Sections", response.code() + " " + response.message());


                Log.e("body", response.body().get(0).getName());
                mServiceMvp.refreshList(response.body());
            }

            @Override
            public void onFailure(Call<List<SectionsModel>> call, Throwable t) {
                Log.e("Exception", call + " " + t);
            }
        });

    }
}
