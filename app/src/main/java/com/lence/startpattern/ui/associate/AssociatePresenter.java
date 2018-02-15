package com.lence.startpattern.ui.associate;

import android.util.Log;

import com.lence.startpattern.api.App;
import com.lence.startpattern.model.AssociateModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssociatePresenter {
    AssociateMvp mMvp;

    public AssociatePresenter(AssociateMvp mvp) {
        mMvp = mvp;
    }

    void startProcedure() {
        mMvp.startProcedure();
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
