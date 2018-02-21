package com.lence.startpattern.ui.service;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.model.SectionsModel;
import com.lence.startpattern.ui.procedure.ProcedureFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceFragment extends Fragment implements ServiceMvp {
    GridView gridView;
    ArrayList<SectionsModel> list = new ArrayList<>();

    public ServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.service, container, false);
        TextView label = (TextView) getActivity().findViewById(R.id.label);
        label.setVisibility(View.VISIBLE);
        label.setText("Услуга");
        // ChangeStyle.whiteColor(getActivity());
        ServicePresenter presenter = new ServicePresenter(this);
        ButterKnife.bind(this, view);
        presenter.loadSections();
        gridView = (GridView) view.findViewById(R.id.gridview);

        // gridView.setAdapter(new ServiceAdapter(getActivity(), list));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProcedureFragment fragment = new ProcedureFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", list.get(position).getId());
                fragment.setArguments(bundle);
                android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack("stack");
                ft.commit();
            }
        });


        return view;
    }

    @Override
    public void refreshList(List<SectionsModel> body) {
        list = (ArrayList<SectionsModel>) body;
        gridView.setAdapter(new ServiceAdapter(getContext(), body));
    }
}
