package com.lence.startpattern.ui.doctor.review;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lence.startpattern.R;
import com.lence.startpattern.model.DoctorReviewsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("ValidFragment")
public class DoctorReviewFragment extends Fragment implements DoctorReviewMvp {
    RecyclerView recyclerView;
    DoctorReviewAdapter mAdapter;
    DoctorReviewPresenter mPresenter;
    @BindView(R.id.createReview)
    Button mCreateReview;
    private int mDoctorId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mDoctorId = getActivity().getIntent().getIntExtra("id", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctor_review, container, false);
        ButterKnife.bind(this, view);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        Log.e("doctorId", "doctorId " + mDoctorId);
        mPresenter = new DoctorReviewPresenter(this);
        if (mDoctorId != 0) {
            mPresenter.loadDoctorReview(mDoctorId);
        }
        return view;
    }

    @Override
    public void refreshList(List<DoctorReviewsModel> body) {
        mAdapter = new DoctorReviewAdapter(body, mPresenter);
        recyclerView.setAdapter(mAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }


    @OnClick(R.id.createReview)
    public void onViewClicked() {
        CreateReviewDialog dialog = new CreateReviewDialog();
        dialog.show(getActivity().getSupportFragmentManager(), "name");
    }
}
