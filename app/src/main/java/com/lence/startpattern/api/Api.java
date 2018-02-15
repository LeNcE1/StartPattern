package com.lence.startpattern.api;


import com.lence.startpattern.model.AssociateModel;
import com.lence.startpattern.model.SectionsModel;
import com.lence.startpattern.model.ServicesModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("/api/sections")
    Call<List<SectionsModel>> getSections();

    @GET("/api/sections/{section}/services")
    Call<List<ServicesModel>> getServices(@Path("section") int id);

    @GET("/api/clinics")
    Call<ResponseBody> getClinics();

    @GET("/api/cities/{city}/clinics")
    Call<ResponseBody> getClinicsInCity(@Path("city") int id); //не работает
    // TODO: 15.02.2018 уточнить тип city id

    @GET("/api/doctors")
    Call<List<AssociateModel>> getDoctors();

}
