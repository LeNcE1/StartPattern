package com.lence.startpattern.utils;


import android.support.v4.app.FragmentManager;

public abstract class BackStackTools {

    public static void clearStack(FragmentManager fm) {
        int count = fm.getBackStackEntryCount();
        while (count > 0) {
            fm.popBackStack();
            count--;
        }

    }
}
