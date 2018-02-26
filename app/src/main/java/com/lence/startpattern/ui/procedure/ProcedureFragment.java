package com.lence.startpattern.ui.procedure;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.SingletonStorage;
import com.lence.startpattern.ui.selectionScreen.SelectionScreenFragment;
import com.lence.startpattern.utils.ChangeStyle;

import java.util.List;


public class ProcedureFragment extends Fragment implements ProcedureMvp {
    ProcedurePresenter pr;
    RecyclerView recyclerView;
    Object mAdapter;
//    ProcedureAdapter mProcedureAdapter;
//    ProcedureDoctorAdapter mProcedureDoctorAdapter;
    Bundle args;

    public ProcedureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.procedure, container, false);
        ChangeStyle.whiteColor(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        pr = new ProcedurePresenter(this);
        TextView label = (TextView) getActivity().findViewById(R.id.label);
        label.setText("Процедура");

        args = getArguments();
        //Log.e("bundle", "bundle" + args.getInt("id"));
        if (args.getInt("id", 0) != 0) {
            pr.loadSections(args.getInt("id"));
        } else {
            //Log.e("doctorId", "doctorId " + args.getInt("doctorId"));
            pr.loadDoctorSections(args.getInt("doctorId"));
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

// TODO: 26.02.2018 доделать оформление заказа через сотрудника 
        return view;
    }


    @Override
    public void startDateSelection() {
        SelectionScreenFragment fragment = new SelectionScreenFragment();
        getActivity().getSupportFragmentManager().popBackStack();
        SingletonStorage.getInstance().setAssociateId(args.getInt("doctorId", 0));
        SingletonStorage.getInstance().setAssociateName(args.getString("doctorName", ""));
        //BackStackTools.clearStack(getActivity().getSupportFragmentManager());
        android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        //ft.addToBackStack("stack");
        ft.commit();
    }

    @Override
    public void refreshList(List<Object> body) {
            mAdapter = new ProcedureAdapter(body, pr);
            recyclerView.setAdapter((ProcedureAdapter) mAdapter);
            recyclerView.getAdapter().notifyDataSetChanged();
        }
//        mProcedureAdapter = new ProcedureAdapter(body, pr);
//        recyclerView.setAdapter(mProcedureAdapter);
//        recyclerView.getAdapter().notifyDataSetChanged();
  //  }

//    @Override
//    public void refreshList(List<AssociateServicesModel> body) {
//        mProcedureDoctorAdapter = new ProcedureDoctorAdapter(body, pr);
//        recyclerView.setAdapter(mProcedureDoctorAdapter);
//        recyclerView.getAdapter().notifyDataSetChanged();
//    }
}
