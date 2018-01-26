package com.lence.startpattern.ui.doctor;


import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.utils.ToolbarColorizeHelper;

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
        TextView label = (TextView) getActivity().findViewById(R.id.label);
        label.setVisibility(View.GONE);
        View appBarLayout = getActivity().findViewById(R.id.appBar);
        appBarLayout.setBackgroundResource(R.color.blue);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout.setElevation(0);
        }
        ImageView logo = getActivity().findViewById(R.id.logo);
        logo.setVisibility(View.GONE);
        ImageView arrowBack = getActivity().findViewById(R.id.arrowBack);
        arrowBack.setColorFilter(getResources().getColor(R.color.white));
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(R.color.blue);
        ToolbarColorizeHelper.colorizeToolbar(toolbar,getResources().getColor(R.color.white),getActivity());

        ButterKnife.bind(this, view);
        mPresenter = new DoctorPresenter(this);




// TODO: 24.01.2018 change toolbar вставить автарку и текст в бар и гонить на выборе услуги


        PagerAdapter adapter = new PagerAdapter(getContext(), getFragmentManager());
        mVp.setAdapter(adapter);
        mTab.setupWithViewPager(mVp);

        return view;
    }


}
