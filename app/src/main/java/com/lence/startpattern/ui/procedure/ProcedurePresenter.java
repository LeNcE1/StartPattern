package com.lence.startpattern.ui.procedure;


import android.util.Log;

import com.lence.startpattern.api.App;
import com.lence.startpattern.model.ServicesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProcedurePresenter {
    ProcedureMvp mMvp;

    public ProcedurePresenter(ProcedureMvp mvp) {
        mMvp = mvp;
    }

    public void startProcedure() {
        mMvp.startDateSelection();
    }

    public void loadSections(int id) {
        Log.e("load", "load");
        App.getApi().getServices(id).enqueue(new Callback<List<ServicesModel>>() {
            @Override
            public void onResponse(Call<List<ServicesModel>> call, Response<List<ServicesModel>> response) {
//                Log.e("Sections", response.code() + " " + response.message());
//                Log.e("body", response.body().get(0).getName());
                mMvp.refreshList(response.body());
            }

            @Override
            public void onFailure(Call<List<ServicesModel>> call, Throwable t) {

            }
        });

    }

    public void loadDoctorSections(int doctorId) {
    }
}
