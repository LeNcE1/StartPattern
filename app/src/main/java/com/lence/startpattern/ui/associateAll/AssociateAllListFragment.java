package com.lence.startpattern.ui.associateAll;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.model.AssociateModel;
import com.lence.startpattern.ui.doctor.DoctorActivity;
import com.lence.startpattern.utils.ChangeStyle;

import java.util.ArrayList;
import java.util.List;


public class AssociateAllListFragment extends Fragment implements AssociateAllMvp {
    RecyclerView recyclerView;
    AssociateAllAdapter mAssociateAllAdapter;
    AssociateAllPresenter pr;
    List<String> posts = new ArrayList<>();
    ProgressDialog dialog;

    public AssociateAllListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.associate_all, container, false);
        FragmentManager fm = getFragmentManager();
        //ProgressDialog dialog;
        dialog = new ProgressDialog(getActivity(),R.style.full_screen_dialog){
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
    public void startDoctor(int id, String name, String spec, String image, int rate) {
        Intent intent = new Intent(getActivity(),DoctorActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("spec", spec);
        intent.putExtra("image", image);
        intent.putExtra("rate", rate);
        startActivity(intent);

//        DoctorActivity fragment = new DoctorActivity();
//        Bundle bundle = new Bundle();
//        bundle.putInt("id", id);
//        bundle.putString("name", name);
//        bundle.putString("spec", spec);
//        bundle.putString("image", image);
//        bundle.putInt("rate", rate);
//        fragment.setArguments(bundle);
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .hide(this)
//                .replace(R.id.content, fragment,"Doctor")
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                //.addToBackStack("stackDoctor")
//                .commit();
    }

    @Override
    public void refreshList(List<AssociateModel> body) {
        mAssociateAllAdapter = new AssociateAllAdapter(body, pr, getActivity());
        recyclerView.setAdapter(mAssociateAllAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
        dialog.dismiss();
    }
}
