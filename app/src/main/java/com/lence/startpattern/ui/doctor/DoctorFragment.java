package com.lence.startpattern.ui.doctor;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.ui.dateSelection.DateSelectionFragment;
import com.lence.startpattern.utils.ChangeStyle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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
    @BindView(R.id.nextStep)
    Button mNextStep;

    public DoctorFragment() {

    }


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctor, container, false);

        ChangeStyle.blueColor(getActivity());

        ButterKnife.bind(this, view);
        mPresenter = new DoctorPresenter(this);





        PagerAdapter adapter = new PagerAdapter(getContext(), getFragmentManager());
        mVp.setAdapter(adapter);
        mTab.setupWithViewPager(mVp);

        return view;
    }

    @OnClick(R.id.nextStep)
    public void onViewClicked() {
        DateSelectionFragment fragment = new DateSelectionFragment();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("stack");
        ft.commit();
    }
}
