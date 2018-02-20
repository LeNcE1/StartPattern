package com.lence.startpattern.ui.associateAll;


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
import com.lence.startpattern.ui.doctor.DoctorFragment;
import com.lence.startpattern.utils.ChangeStyle;

import java.util.ArrayList;
import java.util.List;


public class AssociateAllListFragment extends Fragment implements AssociateAllMvp {
    RecyclerView recyclerView;
    AssociateAllAdapter mAssociateAllAdapter;
    AssociateAllPresenter pr;
    List<String> posts = new ArrayList<>();

    public AssociateAllListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.associate_all, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        pr = new AssociateAllPresenter(this);
        TextView label = (TextView) getActivity().findViewById(R.id.label);
        label.setText("Сотрудник");
        pr.loadAssociate();
        ChangeStyle.whiteColor(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);



        return view;
    }

    @Override
    public void startDoctor(int id,String name, String spec, String image) {
        DoctorFragment fragment = new DoctorFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        bundle.putString("name",name);
        bundle.putString("spec",spec);
        bundle.putString("image",image);
        fragment.setArguments(bundle);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("stack");
        ft.commit();
    }

    @Override
    public void refreshList(List<AssociateModel> body) {
        mAssociateAllAdapter = new AssociateAllAdapter(body, pr,getActivity());
        recyclerView.setAdapter(mAssociateAllAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
