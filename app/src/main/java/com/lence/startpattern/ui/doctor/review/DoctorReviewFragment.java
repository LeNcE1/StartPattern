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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("ValidFragment")
public class DoctorReviewFragment extends Fragment implements DoctorReviewMvp {
    RecyclerView recyclerView;
    DoctorReviewAdapter mAdapter;
    DoctorReviewPresenter pr;
    List<String> posts = new ArrayList<>();
    @BindView(R.id.createReview)
    Button mCreateReview;
    private int mDoctorId;

    public DoctorReviewFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mDoctorId = bundle.getInt("doctorId");
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
        pr = new DoctorReviewPresenter(this);
        pr.loadDoctorReview(mDoctorId);
        return view;
    }

    @Override
    public void refreshList(List<DoctorReviewsModel> body) {
        mAdapter = new DoctorReviewAdapter(body, pr);
        recyclerView.setAdapter(mAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }


    @OnClick(R.id.createReview)
    public void onViewClicked() {
        CreateReviewDialog dialog = new CreateReviewDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("doctorId",mDoctorId);

        dialog.setArguments(bundle);
        dialog.show(getActivity().getSupportFragmentManager(),"name");
    }
}
