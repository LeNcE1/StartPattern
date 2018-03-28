package com.lence.startpattern.ui.associate;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lence.startpattern.ui.EntryActivity;
import com.lence.startpattern.R;
import com.lence.startpattern.model.AssociateModel;
import com.lence.startpattern.ui.selectionScreen.SelectionScreenFragment;

import java.util.ArrayList;
import java.util.List;


public class AssociateListActivity extends AppCompatActivity implements AssociateMvp {
    RecyclerView recyclerView;
    AssociateAdapter associateAdapter;
    AssociatePresenter pr;
    List<String> posts = new ArrayList<>();
    ProgressDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.associate);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        pr = new AssociatePresenter(this);
        TextView label = (TextView) findViewById(R.id.label);
        label.setText("СОТРУДНИК");
        pr.loadAssociate();

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
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }


    @Override
    public void startProcedure() {
        SelectionScreenFragment fragment = new SelectionScreenFragment();
        startActivity(new Intent(this, EntryActivity.class));
        getFragmentManager().popBackStack();
    }

    @Override
    public void refreshList(List<AssociateModel> body) {
        associateAdapter = new AssociateAdapter(body, pr,this);
        recyclerView.setAdapter(associateAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
        dialog.dismiss();
    }
}
