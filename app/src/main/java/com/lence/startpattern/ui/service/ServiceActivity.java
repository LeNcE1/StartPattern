package com.lence.startpattern.ui.service;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
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

public class ServiceActivity extends AppCompatActivity implements ServiceMvp {
    GridView gridView;
    ArrayList<SectionsModel> list = new ArrayList<>();
    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service);
        TextView label = (TextView) findViewById(R.id.label);
        label.setVisibility(View.VISIBLE);
        label.setText("Услуга");
        ServicePresenter presenter = new ServicePresenter(this);
        ButterKnife.bind(this);
        presenter.loadSections();

        dialog = new ProgressDialog(this,R.style.full_screen_dialog){
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
        gridView = (GridView) findViewById(R.id.gridview);

        // gridView.setAdapter(new ServiceAdapter(getActivity(), list));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gridView.setVisibility(View.GONE);
                ProcedureFragment fragment = new ProcedureFragment();
                getIntent().putExtra("id_categ", list.get(position).getId());
//                Bundle bundle = new Bundle();
//                bundle.putInt("id", list.get(position).getId());
//                fragment.setArguments(bundle);
                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack("stack");
                ft.commit();
            }
        });
    }



    @Override
    public void refreshList(List<SectionsModel> body) {
        list = (ArrayList<SectionsModel>) body;
        gridView.setAdapter(new ServiceAdapter(this, body));
        dialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gridView.setVisibility(View.VISIBLE);
    }
}
