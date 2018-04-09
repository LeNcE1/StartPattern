package com.lence.startpattern.ui.doctor;


import android.content.Intent;
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
import com.lence.startpattern.ui.procedure.ProcedureFragment;
import com.lence.startpattern.utils.ChangeStyle;
import com.squareup.picasso.Picasso;

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
    ViewPager mViewPager;
    @BindView(R.id.nextStep)
    Button mNextStep;
    @BindView(R.id.spec)
    TextView mSpec;
    Intent args;
    PagerAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctor, container, false);
        ChangeStyle.blueColor(getActivity());
        args = getActivity().getIntent();
        ButterKnife.bind(this, view);
        mPresenter = new DoctorPresenter(this);
        if (args != null) {
            mName.setText(args.getStringExtra("name"));
            mSpec.setText(args.getStringExtra("spec"));
            Picasso.with(getContext())
                    .load(args.getStringExtra("image"))
                    .resize(200, 200)
                    .centerCrop()
                    .into(mAvatar);
        }

        mAdapter = new PagerAdapter(getContext(), getFragmentManager(), args.getIntExtra("id", 0));
        mViewPager.setAdapter(mAdapter);
        mViewPager.getAdapter().notifyDataSetChanged();
        mViewPager.setCurrentItem(1);
        mTab.setupWithViewPager(mViewPager);
        return view;
    }

    @OnClick(R.id.nextStep)
    public void onViewClicked() {
        ProcedureFragment fragment = new ProcedureFragment();
        ChangeStyle.whiteColor(getActivity());
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment, "Procedure")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}

