package com.lence.startpattern.doctor;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lence.startpattern.R;
import com.lence.startpattern.doctor.info.DoctorInfoFragment;
import com.lence.startpattern.doctor.review.DoctorReviewFragment;
import com.lence.startpattern.procedure.ProcedureFragment;


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
        } else if (position == 2){
            return new DoctorReviewFragment();
        } else {
            return new ProcedureFragment();
       }

    }

    @Override
    public int getCount() {
        return 4;
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
            case 3:
                return "Услуги";
            default:
                return null;
        }
    }
}
