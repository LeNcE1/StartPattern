package com.lence.startpattern.ui.doctor;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    ViewPager mVp;
    @BindView(R.id.nextStep)
    Button mNextStep;
    @BindView(R.id.spec)
    TextView mSpec;

    public DoctorFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        PagerAdapter adapter = new PagerAdapter(getContext(),getActivity().getSupportFragmentManager());
        mVp.setAdapter(adapter);
        mVp.getAdapter().notifyDataSetChanged();
        mVp.setCurrentItem(1);
        mTab.setupWithViewPager(mVp);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctor, container, false);

        ChangeStyle.blueColor(getActivity());
        Bundle bundle = getArguments();
        ButterKnife.bind(this, view);
        mPresenter = new DoctorPresenter(this);
        if (bundle != null) {
            mName.setText(bundle.getString("name"));
            mSpec.setText(bundle.getString("spec"));
            Picasso.with(getContext())
                    .load(bundle.getString("image"))
                    .resize(150, 150)
                    .centerCrop()
                    .into(mAvatar);
        }


        PagerAdapter adapter = new PagerAdapter(getContext(),getActivity().getSupportFragmentManager());
        mVp.setAdapter(adapter);
        mVp.getAdapter().notifyDataSetChanged();
        mVp.setCurrentItem(1);
        mTab.setupWithViewPager(mVp);

        return view;
    }

    @OnClick(R.id.nextStep)
    public void onViewClicked() {
        ProcedureFragment fragment = new ProcedureFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("doctorId",bundle.getInt("id"));
        fragment.setArguments(bundle);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("stack");
        ft.commit();
    }

}
