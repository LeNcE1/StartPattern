package com.lence.startpattern.ui.sessionHistory;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lence.startpattern.ui.sessionHistory.future.FutureSessionFragment;
import com.lence.startpattern.ui.sessionHistory.previous.PreviousSessionFragment;

public class SessionHistoryPagerAdapter extends FragmentStatePagerAdapter {
    private Context mContext;
    public SessionHistoryPagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FutureSessionFragment();
        }  else {
            return new PreviousSessionFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Предстоящие сеансы";
            case 1:
                return "Прошедшие";
            default:
                return null;
        }
    }
}
