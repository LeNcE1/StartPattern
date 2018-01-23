package com.lence.startpattern.dateSelection;


import android.content.Context;

import java.util.Calendar;

public interface DateSelectionMvp {
    void showCalendar();
    void showFreeTime(Context context, Calendar thisDay);
    void startOnlineRecord();
}
