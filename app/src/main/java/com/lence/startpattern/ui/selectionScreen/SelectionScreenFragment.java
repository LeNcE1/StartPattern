package com.lence.startpattern.ui.selectionScreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lence.startpattern.R;
import com.lence.startpattern.ui.associate.AssociateListFragment;
import com.lence.startpattern.ui.createRecord.CreateRecordFragment;
import com.lence.startpattern.ui.dateSelection.DateSelectionFragment;
import com.lence.startpattern.ui.service.ServiceFragment;
import com.lence.startpattern.utils.ChangeStyle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectionScreenFragment extends Fragment implements SelectionScreenMvp {
    SelectionScreenPresenter mPresenter;
    @BindView(R.id.procedure)
    CardView mProcedure;
    @BindView(R.id.associate)
    CardView mAssociate;
    @BindView(R.id.date)
    CardView mDate;
    @BindView(R.id.nextStep)
    Button mNextStep;

    public SelectionScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selection_screen, container, false);

        ChangeStyle.whiteColor(getActivity());

        mPresenter = new SelectionScreenPresenter(this);
        ButterKnife.bind(this, view);


        return view;
    }


    @OnClick(R.id.procedure)
    public void onMProcedureClicked() {
        ServiceFragment fragment = new ServiceFragment();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        //ft.addToBackStack("stack");
        ft.commit();
    }

    @OnClick(R.id.associate)
    public void onMAssociateClicked() {
        AssociateListFragment fragment = new AssociateListFragment();
        // TODO: 29.01.2018 add servise bundle
        android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("stack");
        ft.commit();
    }

    @OnClick(R.id.date)
    public void onMDateClicked() {
        DateSelectionFragment fragment = new DateSelectionFragment();
        // TODO: 29.01.2018 add servise bundle
        android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("stack");
        ft.commit();
    }

    @OnClick(R.id.nextStep)
    public void onViewClicked() {
        CreateRecordFragment fragment = new CreateRecordFragment();
        // TODO: 29.01.2018 add servise bundle
        android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("stack");
        ft.commit();
    }
}
