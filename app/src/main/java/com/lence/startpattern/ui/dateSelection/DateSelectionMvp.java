package com.lence.startpattern.ui.dateSelection;


import android.content.Context;

import com.lence.startpattern.model.DateMap;

import java.util.ArrayList;
import java.util.Calendar;

public interface DateSelectionMvp {
    void showFreeTime(Context context, Calendar thisDay);
    void setDates(ArrayList<DateMap> map);
}
