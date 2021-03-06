package com.lence.startpattern.ui.associateAll;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.model.AssociateModel;
import com.lence.startpattern.ui.doctor.DoctorFragment;
import com.lence.startpattern.utils.ChangeStyle;

import java.util.ArrayList;
import java.util.List;


public class AssociateAllListFragment extends Fragment implements AssociateAllMvp {
    RecyclerView recyclerView;
    AssociateAllAdapter mAssociateAllAdapter;
    AssociateAllPresenter mPresenter;
    ProgressDialog dialog;

    public AssociateAllListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.associate_all, container, false);
        dialog = new ProgressDialog(getActivity(),R.style.full_screen_dialog){
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.dialog_progress);
                getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.FILL_PARENT);
            }
        };

       // dialog.setCancelable(false);
        dialog.show();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mPresenter = new AssociateAllPresenter(this);
        TextView label = (TextView) getActivity().findViewById(R.id.label);
        label.setText("СОТРУДНИК");
        mPresenter.loadAssociate();
        ChangeStyle.whiteColor(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);


        return view;
    }

    @Override
    public void startDoctor(int id, String name, String spec, String image, int rate) {
        DoctorFragment fragment = new DoctorFragment();
        getActivity().getIntent().putExtra("id", id);
        getActivity().getIntent().putExtra("name", name);
        getActivity().getIntent().putExtra("spec", spec);
        getActivity().getIntent().putExtra("image", image);
        getActivity().getIntent().putExtra("rate", rate);
        Log.e("intent", String.valueOf(getActivity().getIntent().getIntExtra("id",0)));
        getActivity().getSupportFragmentManager().beginTransaction()
                .hide(this)
                .replace(R.id.content, fragment,"Doctor")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public void refreshList(List<AssociateModel> body) {
        mAssociateAllAdapter = new AssociateAllAdapter(body, mPresenter, getActivity());
        recyclerView.setAdapter(mAssociateAllAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
        dialog.dismiss();
    }
}
