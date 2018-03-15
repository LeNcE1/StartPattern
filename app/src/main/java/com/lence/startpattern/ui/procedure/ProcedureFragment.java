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

import com.lence.startpattern.R;
import com.lence.startpattern.SingletonStorage;
import com.lence.startpattern.ui.EntryActivity;

import java.util.List;


public class ProcedureFragment extends Fragment implements ProcedureMvp {
    ProcedurePresenter pr;
    RecyclerView recyclerView;
    Object mAdapter;
    //    ProcedureAdapter mProcedureAdapter;
//    ProcedureDoctorAdapter mProcedureDoctorAdapter;
    Intent args;
    ProgressDialog dialog;

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

        args = getActivity().getIntent();
        //Log.e("bundle", "bundle" + args.getInt("id"));
        if (args.getIntExtra("id_categ", 0) != 0) {
            pr.loadSections(args.getIntExtra("id_categ",0));
        } else {
            //Log.e("doctorId", "doctorId " + args.getInt("doctorId"));
            pr.loadDoctorSections(args.getIntExtra("id",0));
        }
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

        return view;
    }


    @Override
    public void startDateSelection() {
        SingletonStorage.getInstance().setAssociate(
                args.getIntExtra("id", 0),
                args.getStringExtra("name"),
                args.getStringExtra("spec"),
                args.getStringExtra("image"),
                args.getIntExtra("rate", 0));
        startActivity(new Intent(getActivity(), EntryActivity.class));
    }

    @Override
    public void refreshList(List<Object> body) {
        mAdapter = new ProcedureAdapter(body, pr);
        recyclerView.setAdapter((ProcedureAdapter) mAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
        dialog.dismiss();
    }
}
