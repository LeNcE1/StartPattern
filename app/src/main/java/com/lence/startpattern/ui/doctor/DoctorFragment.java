package com.lence.startpattern.ui.doctor;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
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
    Bundle args;

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
        PagerAdapter adapter = new PagerAdapter(getContext(), getFragmentManager(), args.getInt("id"));
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
        args = getArguments();

        ButterKnife.bind(this, view);
        mPresenter = new DoctorPresenter(this);
        if (args != null) {
            mName.setText(args.getString("name"));
            mSpec.setText(args.getString("spec"));
            Picasso.with(getContext())
                    .load(args.getString("image"))
                    .resize(200, 200)
                    .centerCrop()
                    .into(mAvatar);
        }


        PagerAdapter adapter = new PagerAdapter(getContext(), getFragmentManager(), args.getInt("id"));
        mVp.setAdapter(adapter);
        mVp.getAdapter().notifyDataSetChanged();
        mVp.setCurrentItem(1);
        mTab.setupWithViewPager(mVp);

        return view;
    }

    @OnClick(R.id.nextStep)
    public void onViewClicked() {
        ProcedureFragment fragment = new ProcedureFragment();
        //getFragmentManager().popBackStack();
        Bundle bundle = new Bundle();
        Log.e("doctor", "id " + args.getInt("id"));
        bundle.putInt("doctorId", args.getInt("id"));
        bundle.putString("doctorName", args.getString("name"));
        bundle.putString("doctorSpec", args.getString("spec"));
        bundle.putString("doctorImage", args.getString("image"));
        bundle.putInt("doctorRate", args.getInt("rate"));
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .hide(this)
                .replace(R.id.content, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack("stack")
                .commit();
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onPause() {
        super.onPause();

    }
}

