package com.lence.startpattern.ui.procedure;


import android.util.Log;

import com.lence.startpattern.api.App;
import com.lence.startpattern.model.AssociateServicesModel;
import com.lence.startpattern.model.ServicesModel;

import java.util.ArrayList;
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
                //mMvp.refreshListServicesModel(response.body());
                ArrayList<Object> l= new ArrayList<Object>(response.body());
                mMvp.refreshList(l);
            }

            @Override
            public void onFailure(Call<List<ServicesModel>> call, Throwable t) {
                Log.e("Exception", call + " " + t);
            }
        });

    }

    public void loadDoctorSections(int doctorId) {
        Log.e("load", "load");
        App.getApi().getDoctorServices(doctorId).enqueue(new Callback<List<AssociateServicesModel>>() {
            @Override
            public void onResponse(Call<List<AssociateServicesModel>> call, Response<List<AssociateServicesModel>> response) {
                //Log.e("Sections", response.code() + " " + response.message());
               // Log.e("body", response.body().get(0).getName());
               // mMvp.refreshListDoctorServicesModel(response.body());
                ArrayList<Object> l= new ArrayList<Object>(response.body());
                mMvp.refreshList(l);
            }

            @Override
            public void onFailure(Call<List<AssociateServicesModel>> call, Throwable t) {
                Log.e("Exception", call + " " + t);
            }
        });
    }
}
