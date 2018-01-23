package com.lence.startpattern.createRecord;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.onlineRecord.OnlineRecordFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CreateRecordFragment extends Fragment implements CreateRecordMvp {
    CreateRecordPresenter mPresenter;
    @BindView(R.id.enter)
    Button mEnter;

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
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                .replace(R.id.content, fragment)
                .addToBackStack("myStack")
                .commit();

    }
}
