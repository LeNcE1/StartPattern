package com.lence.startpattern.onlineRecord;


import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lence.startpattern.R;

import butterknife.ButterKnife;


public class OnlineRecordFragment extends Fragment implements OnlineRecordMvp {
OnlineRecordPresenter mPresenter;

    public OnlineRecordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.online_record, container, false);
        mPresenter = new OnlineRecordPresenter(this);
        ButterKnife.bind(this, view);
        TextView label = (TextView) getActivity().findViewById(R.id.label);
        label.setText("Записаться");


        return view;
    }

}
