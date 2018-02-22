package com.lence.startpattern.ui.associate;


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
import com.lence.startpattern.model.AssociateModel;
import com.lence.startpattern.ui.selectionScreen.SelectionScreenFragment;

import java.util.ArrayList;
import java.util.List;


public class AssociateListFragment extends Fragment implements AssociateMvp {
    RecyclerView recyclerView;
    AssociateAdapter associateAdapter;
    AssociatePresenter pr;
    List<String> posts = new ArrayList<>();

    public AssociateListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.associate, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        pr = new AssociatePresenter(this);
        TextView label = (TextView) getActivity().findViewById(R.id.label);
        label.setText("Сотрудник");
        pr.loadAssociate();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);



        return view;
    }

    @Override
    public void startProcedure() {
        SelectionScreenFragment fragment = new SelectionScreenFragment();
        getActivity().getSupportFragmentManager().popBackStack();
        // TODO: 29.01.2018 add associate bundle
        android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        //ft.addToBackStack("stack");
        ft.commit();
    }

    @Override
    public void refreshList(List<AssociateModel> body) {
        associateAdapter = new AssociateAdapter(body, pr,getActivity());
        recyclerView.setAdapter(associateAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
