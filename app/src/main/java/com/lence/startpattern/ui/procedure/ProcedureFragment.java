package com.lence.startpattern.ui.procedure;


import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.ui.dateSelection.DateSelectionFragment;

import java.util.ArrayList;
import java.util.List;


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


    @Override
    public void startDateSelection() {
        DateSelectionFragment fragment = new DateSelectionFragment();
        android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("stack");
        ft.commit();
    }
}