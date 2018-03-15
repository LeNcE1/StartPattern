package com.lence.startpattern.ui.procedure;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lence.startpattern.ui.EntryActivity;
import com.lence.startpattern.R;
import com.lence.startpattern.SingletonStorage;

import java.util.List;


public class ProcedureFragment extends Fragment implements ProcedureMvp {
    ProcedurePresenter pr;
    RecyclerView recyclerView;
    Object mAdapter;
    //    ProcedureAdapter mProcedureAdapter;
//    ProcedureDoctorAdapter mProcedureDoctorAdapter;
    Bundle args;
    ProgressDialog dialog;

    public ProcedureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.procedure, container, false);
        //ChangeStyle.whiteColor(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        pr = new ProcedurePresenter(this);

        // TODO: 14.03.2018 разделить на 2 разных фрагмента с разной версткой и адаптерами, чтобы не было костыля

        if (getActivity() instanceof EntryActivity) {
            //костыль
            TextView label = (TextView) getActivity().findViewById(R.id.label);
            label.setText("Процедура");
            View bar = view.findViewById(R.id.bar);
            bar.setVisibility(View.GONE);
        }

        dialog = new ProgressDialog(getActivity(), R.style.full_screen_dialog) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.dialog_progress);
                getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.FILL_PARENT);
            }
        };

        dialog.setCancelable(false);
        dialog.show();

        args = getArguments();
        //Log.e("bundle", "bundle" + args.getInt("id"));
        if (args.getInt("id", 0) != 0) {
            pr.loadSections(args.getInt("id"));
        } else {
            //Log.e("doctorId", "doctorId " + args.getInt("doctorId"));
            pr.loadDoctorSections(args.getInt("doctorId"));
        }

        //mProcedureAdapter = new ProcedureAdapter(posts,pr);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        //recyclerView.setAdapter(mProcedureAdapter);
        //recyclerView.getAdapter().notifyDataSetChanged();

// TODO: 26.02.2018 доделать оформление заказа через сотрудника, исправить баг с кнопкой назад
        return view;
    }


    @Override
    public void startDateSelection() {
        SingletonStorage.getInstance().setAssociate(
                args.getInt("doctorId", 0),
                args.getString("doctorName", ""),
                args.getString("doctorSpec", ""),
                args.getString("doctorImage", ""),
                args.getInt("doctorRate", 0));
        startActivity(new Intent(getActivity(), EntryActivity.class));
    }

    @Override
    public void refreshList(List<Object> body) {
        mAdapter = new ProcedureAdapter(body, pr);
        recyclerView.setAdapter((ProcedureAdapter) mAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
        dialog.dismiss();
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
