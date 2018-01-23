package com.lence.startpattern.dateSelection;


import android.content.Context;

public interface DateSelectionMvp {
    void showCalendar();
    void showFreeTime(Context context, int year, int month, int dayOfMonth);
    void startOnlineRecord();
}
