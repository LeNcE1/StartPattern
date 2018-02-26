package com.lence.startpattern.ui.doctor;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lence.startpattern.ui.doctor.info.DoctorInfoFragment;
import com.lence.startpattern.ui.doctor.review.DoctorReviewFragment;


public class PagerAdapter extends FragmentStatePagerAdapter {
    private Context mContext;
    private int mDoctorId;

    public PagerAdapter(Context context, FragmentManager fm, int doctorId) {
        super(fm);
        mContext = context;
        mDoctorId = doctorId;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new DoctorInfoFragment();
        } else if (position == 1){
            return addArgsInDoctorReviewFragment(mDoctorId);
        } else {
            return new DoctorInfoFragment();
        }
    }

    private DoctorReviewFragment addArgsInDoctorReviewFragment(int mDoctorId){
        final DoctorReviewFragment doctorReviewFragment = new DoctorReviewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("doctorId",mDoctorId);
        doctorReviewFragment.setArguments(bundle);
        return doctorReviewFragment;
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
