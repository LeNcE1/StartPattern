package com.lence.startpattern.ui.doctor.review;


import android.util.Log;

import com.lence.startpattern.api.App;
import com.lence.startpattern.model.DoctorReviewsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorReviewPresenter {
    DoctorReviewMvp mMvp;

    public DoctorReviewPresenter(DoctorReviewMvp mvp) {
        mMvp = mvp;
    }

    public void loadDoctorReview(int doctorId) {
        Log.e("load", "load");
        App.getApi().getDoctorReviews(doctorId).enqueue(new Callback<List<DoctorReviewsModel>>() {
            @Override
            public void onResponse(Call<List<DoctorReviewsModel>> call, Response<List<DoctorReviewsModel>> response) {
                //Log.e("Sections", response.code() + " " + response.message());
                //Log.e("body", response.body().get(0).getName());
                mMvp.refreshList(response.body());
            }

            @Override
            public void onFailure(Call<List<DoctorReviewsModel>> call, Throwable t) {

            }
        });
    }
}
