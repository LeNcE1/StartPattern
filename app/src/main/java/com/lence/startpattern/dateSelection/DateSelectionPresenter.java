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
        for (int i = -3; i < count+5; i++) {
            data.add(i);
        }
        return data;
    }
}
