package com.lence.startpattern.service;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.associate.AssociateListFragment;

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
        ServicePresenter presenter = new ServicePresenter(this);
        ButterKnife.bind(this, view);
        TextView label = (TextView) getActivity().findViewById(R.id.label);
        label.setText("Услуга");
        GridView gridView= (GridView) view.findViewById(R.id.gridview);
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add("Услуга "+i);
        }
        gridView.setAdapter(new ServiceAdapter(getActivity(),list));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AssociateListFragment associateListFragment =new AssociateListFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                        .replace(R.id.content, associateListFragment)
                        .addToBackStack("myStack")
                        .commit();
            }
        });


        return view;
    }

}
