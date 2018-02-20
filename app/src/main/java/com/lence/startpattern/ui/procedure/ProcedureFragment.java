package com.lence.startpattern.ui.procedure;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.SingletonStorage;
import com.lence.startpattern.model.ServicesModel;
import com.lence.startpattern.ui.selectionScreen.SelectionScreenFragment;

import java.util.List;


public class ProcedureFragment extends Fragment implements ProcedureMvp{
ProcedurePresenter pr;
    RecyclerView recyclerView;
    ProcedureAdapter mProcedureAdapter;


    public ProcedureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.procedure, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        pr = new ProcedurePresenter(this);
        TextView label = (TextView) getActivity().findViewById(R.id.label);
        label.setText("Процедура");

        Bundle bundle = getArguments();
        Log.e("bundle","bundle"+bundle.toString());
        if(bundle.getInt("id",0) != 0) {
            pr.loadSections(bundle.getInt("id"));
        }
        else {
            pr.loadDoctorSections(bundle.getInt("doctorId"));
        }
        SingletonStorage.getInstance().setAssociateId(0);
        SingletonStorage.getInstance().setServicesDescription("");
        SingletonStorage.getInstance().setAssociateName("");
        SingletonStorage.getInstance().setServicesId(0);
        SingletonStorage.getInstance().setDate("");
        SingletonStorage.getInstance().setTime("");
        //mProcedureAdapter = new ProcedureAdapter(posts,pr);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        //recyclerView.setAdapter(mProcedureAdapter);
        //recyclerView.getAdapter().notifyDataSetChanged();


        return view;
    }


    @Override
    public void startDateSelection() {
        SelectionScreenFragment fragment = new SelectionScreenFragment();
        android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("stack");
        ft.commit();
    }

    @Override
    public void refreshList(List<ServicesModel> body) {
        mProcedureAdapter = new ProcedureAdapter(body,pr);
        recyclerView.setAdapter(mProcedureAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
