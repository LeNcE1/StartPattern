package com.lence.startpattern.ui.doctor;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lence.startpattern.ui.doctor.info.DoctorInfoFragment;
import com.lence.startpattern.ui.doctor.review.DoctorReviewFragment;


public class PagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new DoctorInfoFragment();
        } else if (position == 1){
            return new DoctorReviewFragment();
        } else {
            return new DoctorReviewFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "Информация";
            case 1:
                return "Отзывы";
            case 2:
                return "Расписание";
            default:
                return null;
        }
    }
}
