package com.lence.startpattern.procedure;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.associate.AssociateAdapter;
import com.lence.startpattern.associate.AssociateListFragment;
import com.lence.startpattern.associate.AssociatePresenter;
import com.lence.startpattern.service.ServiceAdapter;
import com.lence.startpattern.service.ServicePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class ProcedureFragment extends Fragment implements ProcedureMvp{
ProcedurePresenter pr;
    RecyclerView recyclerView;
    ProcedureAdapter mProcedureAdapter;

    List<String> posts = new ArrayList<>();
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
        for (int i = 0; i < 10; i++) {
            posts.add("Процедура " + i);

        }
        mProcedureAdapter = new ProcedureAdapter(posts,pr);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mProcedureAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();


        return view;
    }


}
