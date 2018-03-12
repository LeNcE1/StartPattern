package com.lence.startpattern.ui.createRecord;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.ui.onlineRecord.OnlineRecordFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CreateRecordFragment extends Fragment implements CreateRecordMvp {
    CreateRecordPresenter mPresenter;
    @BindView(R.id.enter)
    Button mEnter;
    @BindView(R.id.procedure)
    TextView mProcedure;
    @BindView(R.id.avatar)
    ImageView mAvatar;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.spec)
    TextView mSpec;
    @BindView(R.id.ratingBar)
    RatingBar mRatingBar;
    @BindView(R.id.count_review)
    TextView mCountReview;
    @BindView(R.id.doctorSelect)
    RelativeLayout mDoctorSelect;
    @BindView(R.id.date)
    TextView mDate;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.timeSelect)
    RelativeLayout mTimeSelect;

    public CreateRecordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_record, container, false);
        mPresenter = new CreateRecordPresenter(this);
        ButterKnife.bind(this, view);
        TextView label = (TextView) getActivity().findViewById(R.id.label);
        label.setText("Создать запись");


        return view;
    }


    @OnClick(R.id.enter)
    public void onViewClicked() {
        OnlineRecordFragment fragment = new OnlineRecordFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("stack");
        ft.commit();

    }





}
