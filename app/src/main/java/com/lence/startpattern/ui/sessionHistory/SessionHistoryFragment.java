package com.lence.startpattern.ui.sessionHistory;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lence.startpattern.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SessionHistoryFragment extends Fragment implements SessionHistoryMvp {
    SessionHistoryPresenter mPresenter;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mViewPager;

    public SessionHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.session_history, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new SessionHistoryPresenter(this);

        SessionHistoryPagerAdapter adapter = new SessionHistoryPagerAdapter(getContext(), getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.getAdapter().notifyDataSetChanged();
        mViewPager.setCurrentItem(0);
        mTab.setupWithViewPager(mViewPager);
        return view;
    }

}
