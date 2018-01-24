package com.lence.startpattern.doctor;


import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.lence.startpattern.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DoctorFragment extends Fragment implements DoctorMvp {
    DoctorPresenter mPresenter;
    @BindView(R.id.avatar)
    ImageView mAvatar;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.head)
    RelativeLayout mHead;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;

    public DoctorFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctor, container, false);

        ButterKnife.bind(this, view);
        mPresenter = new DoctorPresenter(this);




// TODO: 24.01.2018 change toolbar

        PagerAdapter adapter = new PagerAdapter(getContext(), getFragmentManager());
        mVp.setAdapter(adapter);
        mTab.setupWithViewPager(mVp);

        return view;
    }


}
