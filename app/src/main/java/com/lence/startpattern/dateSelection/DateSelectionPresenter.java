package com.lence.startpattern.dateSelection;


import java.util.ArrayList;
import java.util.List;

public class DateSelectionPresenter {
    DateSelectionMvp mMvp;

    public DateSelectionPresenter(DateSelectionMvp mvp) {
        mMvp = mvp;
    }

    public List<Integer> getData(int count) {
        List<Integer> data = new ArrayList<>();
        for (int i = -2; i < count+4; i++) {
            data.add(i);
        }
        return data;
    }

    public void startOnlineRecord(){
        mMvp.startOnlineRecord();
    }
}
