package com.lence.startpattern.doctor.review;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lence.startpattern.R;
import com.lence.startpattern.associate.AssociateAdapter;
import com.lence.startpattern.associate.AssociatePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class DoctorReviewFragment extends Fragment implements DoctorReviewMvp{
    RecyclerView recyclerView;
    DoctorReviewAdapter mAdapter;
    DoctorReviewPresenter pr;
    List<String> posts = new ArrayList<>();

    public DoctorReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctor_review, container, false);
        ButterKnife.bind(this, view);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        for (int i = 0; i < 10; i++) {
            posts.add("User " + i);

        }
        mAdapter = new DoctorReviewAdapter(posts,pr);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
        return view;
    }

}
