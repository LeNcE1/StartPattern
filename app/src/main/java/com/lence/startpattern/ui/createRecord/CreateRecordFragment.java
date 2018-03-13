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
import com.lence.startpattern.SingletonStorage;
import com.lence.startpattern.ui.onlineRecord.OnlineRecordFragment;
import com.squareup.picasso.Picasso;

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
    @BindView(R.id.price)
    TextView mPrice;

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
        SingletonStorage storage = SingletonStorage.getInstance();
        mProcedure.setText(storage.getServicesDescription());
        Picasso.with(getContext())
                .load(storage.getAssociateImage())
                .resize(200, 200)
                .centerCrop()
                .into(mAvatar);
        mName.setText(storage.getAssociateName());
        mSpec.setText(storage.getAssociateDescription());
        mRatingBar.setRating(storage.getAssociateRate());
        mPrice.setText(String.valueOf(storage.getServicesPrice()));
        mDate.setText(storage.getDate());
        mTime.setText(storage.getTime());

        return view;
    }


    @OnClick(R.id.enter)
    public void onViewClicked() {
        OnlineRecordFragment fragment = new OnlineRecordFragment();
        getFragmentManager().beginTransaction()
                .hide(this)
                .replace(R.id.content, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack("stack")
                .commit();

    }


}
