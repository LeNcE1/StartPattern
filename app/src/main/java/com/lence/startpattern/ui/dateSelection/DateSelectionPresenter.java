package com.lence.startpattern.ui.dateSelection;


import android.util.Log;

import com.lence.startpattern.api.App;
import com.lence.startpattern.model.DateMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DateSelectionPresenter {
    DateSelectionMvp mMvp;

    public DateSelectionPresenter(DateSelectionMvp mvp) {
        mMvp = mvp;
    }

    public List<Integer> getData(int count) {
        List<Integer> data = new ArrayList<>();
        for (int i = -2; i < count + 4; i++) {
            data.add(i);
        }
        return data;
    }

    public void loadTimeDoctor(int id) {
        App.getApi().getTimeDoctor(id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("TimeDoctor", response.code() + " " + response.message());
                try {
                    // Log.e("TimeDoctor", response.body().string().toString());
                    JSONObject object = new JSONObject(response.body().string());
                    ArrayList<DateMap> map = new ArrayList<>();
                    JSONArray names = object.names();
                    for (int i = 0; i<names.length(); i++){
                        ArrayList<String> arrayList = new ArrayList<>();
                        JSONArray arr = object.getJSONArray(names.getString(i));
                        for (int j=0; j<arr.length(); j++){
                            arrayList.add(arr.getString(j));
                        }
                        map.add(new DateMap(names.getString(i), arrayList));
                        //Log.e("test", "data " + names.getString(i) + ": " + (arrayList.isEmpty()?0:arrayList.get(0)) + "!!!!");
                    }


                    mMvp.setDates(map);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public void startOnlineRecord() {
        mMvp.startOnlineRecord();
    }
}
