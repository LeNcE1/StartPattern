package com.lence.startpattern.ui.selectionScreen;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.SingletonStorage;
import com.lence.startpattern.ui.associate.AssociateListActivity;
import com.lence.startpattern.ui.createRecord.CreateRecordFragment;
import com.lence.startpattern.ui.dateSelection.DateSelectionActivity;
import com.lence.startpattern.ui.service.ServiceActivity;
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
    @BindView(R.id.textProcedure)
    TextView mTextProcedure;
    @BindView(R.id.textAssociate)
    TextView mTextAssociate;
    @BindView(R.id.textDate)
    TextView mTextDate;

    public SelectionScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selection_screen, container, false);
        TextView label = (TextView) getActivity().findViewById(R.id.label);
        label.setVisibility(View.VISIBLE);
        label.setText("Онлайн запись");
        ChangeStyle.whiteColor(getActivity());
        // TODO: 07.03.2018 дропнуть кнопку назад на экране 
        mPresenter = new SelectionScreenPresenter(this);
        ButterKnife.bind(this, view);

        Log.e("Storage", SingletonStorage.getInstance().getServicesId() + " "
                + SingletonStorage.getInstance().getServicesDescription() + " "
                + SingletonStorage.getInstance().getAssociateId() + " "
                + SingletonStorage.getInstance().getAssociateName() + " "
                + SingletonStorage.getInstance().getDate() + " "
                + SingletonStorage.getInstance().getTime());

        mTextProcedure.setText(SingletonStorage.getInstance().getServicesDescription().equals("") ? "Выберите услугу" : SingletonStorage.getInstance().getServicesDescription());
        mTextAssociate.setText(SingletonStorage.getInstance().getAssociateName().equals("") ? "Выберите специалиста" : SingletonStorage.getInstance().getAssociateName());
        mTextDate.setText(SingletonStorage.getInstance().getDate().equals("") ? "Выберите время" : SingletonStorage.getInstance().getDate() + " " + SingletonStorage.getInstance().getTime());

        enable(mAssociate,false);
        enable(mDate,false);
        mNextStep.setEnabled(false);
        if (!SingletonStorage.getInstance().getServicesDescription().equals("")) {
            enable(mAssociate,true);
        }
        if (!SingletonStorage.getInstance().getAssociateName().equals("")) {
            enable(mDate,true);
        }
        if (!SingletonStorage.getInstance().getDate().equals("")) {
            mNextStep.setEnabled(true);
            mNextStep.setTextColor(Color.WHITE);
        }
        return view;
    }

    private void enable(View view,boolean en) {
        view.setEnabled(en);
        if (!en)
            view.setBackgroundColor(getResources().getColor(R.color.disabledGray));
        else
            view.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.procedure)
    public void onMProcedureClicked() {
        startActivity(new Intent(getActivity(), ServiceActivity.class));
//        ServiceActivity fragment = new ServiceActivity();
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.content, fragment);
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//        ft.addToBackStack("stack");
//        ft.commit();
    }

    @OnClick(R.id.associate)
    public void onMAssociateClicked() {
        startActivity(new Intent(getActivity(), AssociateListActivity.class));
//        AssociateListActivity fragment = new AssociateListActivity();
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.content, fragment);
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//        ft.addToBackStack("stack");
//        ft.commit();
    }

    @OnClick(R.id.date)
    public void onMDateClicked() {
        startActivity(new Intent(getActivity(), DateSelectionActivity.class));
//        DateSelectionActivity fragment = new DateSelectionActivity();
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.content, fragment);
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//        ft.addToBackStack("stack");
//        ft.commit();
    }

    @OnClick(R.id.nextStep)
    public void onViewClicked() {
        CreateRecordFragment fragment = new CreateRecordFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("stack");
        ft.commit();
    }


}
