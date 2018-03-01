package com.lence.startpattern.ui.sessionHistory.previous;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lence.startpattern.R;
import com.lence.startpattern.ui.sessionHistory.SessionHistoryAdapter;
import com.lence.startpattern.ui.sessionHistory.SessionHistoryMvp;
import com.lence.startpattern.ui.sessionHistory.SessionHistoryPresenter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreviousSessionFragment extends Fragment implements SessionHistoryMvp {

    RecyclerView mRecyclerView;
    Unbinder unbinder;
    SessionHistoryPresenter mPresenter;

    public PreviousSessionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.previous_session, container, false);
        unbinder = ButterKnife.bind(this, view);
        mPresenter = new SessionHistoryPresenter(this);
        ArrayList<Object> l = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            l.add(String.valueOf(i));
        }
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(manager);
        SessionHistoryAdapter adapter = new SessionHistoryAdapter(l, mPresenter);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        return view;
    }


}
