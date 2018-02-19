package com.lence.startpattern.ui.associateAll;

import android.util.Log;

import com.lence.startpattern.api.App;
import com.lence.startpattern.model.AssociateModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssociateAllPresenter {
    AssociateAllMvp mMvp;

    public AssociateAllPresenter(AssociateAllMvp mvp) {
        mMvp = mvp;
    }

    void startDoctor(int id,String name, String spec, String image) {
        mMvp.startDoctor(id,name,spec,image);
    }

    public void loadAssociate() {
        Log.e("load", "load");
        App.getApi().getDoctors().enqueue(new Callback<List<AssociateModel>>() {
            @Override
            public void onResponse(Call<List<AssociateModel>> call, Response<List<AssociateModel>> response) {
//                Log.e("Sections", response.code() + " " + response.message());
//                Log.e("body", response.body().get(0).getName());
                mMvp.refreshList(response.body());
            }

            @Override
            public void onFailure(Call<List<AssociateModel>> call, Throwable t) {

            }
        });

    }
}
