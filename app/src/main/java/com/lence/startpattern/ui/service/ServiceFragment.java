package com.lence.startpattern.ui.service;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.ui.associate.AssociateListFragment;
import com.lence.startpattern.utils.ToolbarColorizeHelper;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceFragment extends Fragment implements ServiceMvp {


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
        View appBarLayout = getActivity().findViewById(R.id.appBar);
        appBarLayout.setBackgroundResource(R.color.white);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout.setElevation(8);
        }
        ImageView logo = getActivity().findViewById(R.id.logo);
        logo.setVisibility(View.VISIBLE);
        ImageView arrowBack = getActivity().findViewById(R.id.arrowBack);
        arrowBack.setColorFilter(getResources().getColor(R.color.colorAccent));
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(R.color.white);
        ToolbarColorizeHelper.colorizeToolbar(toolbar, getResources().getColor(R.color.blue), getActivity());

        ServicePresenter presenter = new ServicePresenter(this);
        ButterKnife.bind(this, view);

        GridView gridView = (GridView) view.findViewById(R.id.gridview);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("Услуга " + i);
        }
        gridView.setAdapter(new ServiceAdapter(getActivity(), list));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AssociateListFragment fragment = new AssociateListFragment();
                android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack("stack");
                ft.commit();
            }
        });


        return view;
    }

}
