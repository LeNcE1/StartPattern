package com.lence.startpattern.api;


import com.lence.startpattern.model.AssociateModel;
import com.lence.startpattern.model.AssociateServicesModel;
import com.lence.startpattern.model.DoctorReviewsModel;
import com.lence.startpattern.model.SectionsModel;
import com.lence.startpattern.model.ServicesModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("/api/sections")
    Call<List<SectionsModel>> getSections();

    @GET("/api/sections/{section}/services")
    Call<List<ServicesModel>> getServices(@Path("section") int id);

    @GET("/api/clinics")
    Call<ResponseBody> getClinics();

    @GET("/api/cities/{city}/clinics")
    Call<ResponseBody> getClinicsInCity(@Path("city") int id);

    @GET("/api/doctors")
    Call<List<AssociateModel>> getDoctors();
    // TODO: 13.03.2018 добавить запрос на сотрудника по услуге

    @GET("/api/doctors/{doctor}/services")
    Call<List<AssociateServicesModel>> getDoctorServices(@Path("doctor") int id);

    @GET("/api/doctors/{doctor}/reviews")
    Call<List<DoctorReviewsModel>> getDoctorReviews(@Path("doctor") int id);

    @FormUrlEncoded
    @POST("/api/reviews")
    Call<ResponseBody> postReview(@Field("name") String name,
                                  @Field("text") String text,
                                  @Field("rate") int rate,
                                  @Field("doctor_id") int doctorId);

    @GET("/api/journal")
    Call<ResponseBody> getTimeDoctor(@Query("doctor_id") int id);
}
