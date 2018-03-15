package com.lence.startpattern.ui.doctor;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.ui.EntryActivity;
import com.lence.startpattern.ui.procedure.ProcedureFragment;
import com.lence.startpattern.ui.service.ServiceActivity;
import com.lence.startpattern.ui.sessionHistory.SessionHistoryFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DoctorActivity extends AppCompatActivity implements DoctorMvp, NavigationView.OnNavigationItemSelectedListener {
    DoctorPresenter mPresenter;
    @BindView(R.id.avatar)
    ImageView mAvatar;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.head)
    RelativeLayout mHead;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.nextStep)
    Button mNextStep;
    @BindView(R.id.spec)
    TextView mSpec;
    Intent args;
    PagerAdapter adapter;
    @BindView(R.id.view)
    LinearLayout mView;
    @BindView(R.id.content)
    RelativeLayout mContent;


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
////        if (savedInstanceState != null && savedInstanceState.containsKey("args")) {
////            args = savedInstanceState.getBundle("args");
////            Log.e("saveargs", String.valueOf(args));
////            adapter = new PagerAdapter(getContext(), getFragmentManager(), args.getInt("id"));
////            mVp.setAdapter(adapter);
////            mVp.getAdapter().notifyDataSetChanged();
////            mVp.setCurrentItem(1);
////            mTab.setupWithViewPager(mVp);
////        }
//    }

//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//
//        outState.putBundle("args", args);
//        super.onSaveInstanceState(outState);
////        outState.putInt("id", args.getInt("id"));
////        outState.putString("name", args.getString("name"));
////        outState.putString("spec", args.getString("spec"));
////        outState.putString("image", args.getString("image"));
////        outState.putInt("rate", args.getInt("rate"));
//
//    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        //args = savedInstanceState;
//        Log.e("savedInstanceState", savedInstanceState.toString());
//    }

//        @Override
//    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
////        savedInstanceState = getArguments();
////            Log.e("onViewStateRestored", savedInstanceState.toString());
//            if (savedInstanceState != null && savedInstanceState.containsKey("args")) {
//                args = savedInstanceState.getBundle("args");
//                Log.e("saveargs", String.valueOf(args));
//                adapter = new PagerAdapter(getContext(), getFragmentManager(), args.getInt("id"));
//                mVp.setAdapter(adapter);
//                mVp.getAdapter().notifyDataSetChanged();
//                mVp.setCurrentItem(1);
//                mTab.setupWithViewPager(mVp);
//            }
//        args = savedInstanceState != null ? savedInstanceState : getArguments();
//        //Log.e("args", args.toString());
//        PagerAdapter adapter = new PagerAdapter(getContext(), getFragmentManager(), args.getInt("id"));
//        mVp.setAdapter(adapter);
//        mVp.getAdapter().notifyDataSetChanged();
//        mVp.setCurrentItem(1);
//        mTab.setupWithViewPager(mVp);
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        // toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //ChangeStyle.blueColor(this);
        ButterKnife.bind(this);
        args = getIntent();
        mPresenter = new DoctorPresenter(this);
        if (args != null) {
            mName.setText(args.getStringExtra("name"));
            mSpec.setText(args.getStringExtra("spec"));
            Picasso.with(this)
                    .load(args.getStringExtra("image"))
                    .resize(200, 200)
                    .centerCrop()
                    .into(mAvatar);
        }

        adapter = new PagerAdapter(this, getSupportFragmentManager(), args.getIntExtra("id", 0));
        mVp.setAdapter(adapter);
        mVp.getAdapter().notifyDataSetChanged();
        mVp.setCurrentItem(1);
        mTab.setupWithViewPager(mVp);

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.doctor, container, false);
//        ChangeStyle.blueColor(getActivity());
//         args = savedInstanceState != null ? savedInstanceState : getArguments();
////        if (args == null) {
////            args = getArguments();
////        }
//         Log.e("savedInstanceState", " "+savedInstanceState);
//         Log.e("args", " "+args);
//        ButterKnife.bind(this, view);
//        mPresenter = new DoctorPresenter(this);
//        if (args != null) {
//            mName.setText(args.getString("name"));
//            mSpec.setText(args.getString("spec"));
//            Picasso.with(getContext())
//                    .load(args.getString("image"))
//                    .resize(200, 200)
//                    .centerCrop()
//                    .into(mAvatar);
//        }
//
//        if (adapter == null) {
//            adapter = new PagerAdapter(getContext(), getFragmentManager(), args.getInt("id"));
//            mVp.setAdapter(adapter);
//            mVp.getAdapter().notifyDataSetChanged();
//            mVp.setCurrentItem(1);
//            mTab.setupWithViewPager(mVp);
//        }
//        return view;
//    }

    @OnClick(R.id.nextStep)
    public void onViewClicked() {
        mView.setVisibility(View.GONE);
//        TypedValue tv = new TypedValue();
//        getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
//        int actionBarHeight = getResources().getDimensionPixelSize(tv.resourceId);
//        setMargins(mContent, 0, actionBarHeight, 0, 0);
        ProcedureFragment fragment = new ProcedureFragment();
        Bundle bundle = new Bundle();
        Log.e("doctor", "id " + args.getIntExtra("id", 0));
        bundle.putInt("doctorId", args.getIntExtra("id", 0));
        bundle.putString("doctorName", args.getStringExtra("name"));
        bundle.putString("doctorSpec", args.getStringExtra("spec"));
        bundle.putString("doctorImage", args.getStringExtra("image"));
        bundle.putInt("doctorRate", args.getIntExtra("rate", 0));
        fragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("stack");
        ft.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.online_record) {
            if (!this.getClass().equals(ServiceActivity.class)) {
                startActivity(new Intent(this, EntryActivity.class));
//                SelectionScreenFragment fragment = new SelectionScreenFragment();
//                FragmentManager fm = getSupportFragmentManager();
//                //BackStackTools.clearStack(fm);
//                FragmentTransaction ft = fm.beginTransaction();
//
//                ft.replace(R.id.content, fragment, "SelectionScreen");
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                ft.addToBackStack("stack");
//                ft.commit();
            }
        } else if (id == R.id.doctor) {
            super.onBackPressed();
        } else if (id == R.id.history) {
            FragmentManager fm = getSupportFragmentManager();
            //BackStackTools.clearStack(fm);
            SessionHistoryFragment fragment = new SessionHistoryFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content, fragment, "SessionHistory");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack("stack");
            ft.commit();

//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        TypedValue tv = new TypedValue();
//        getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
//        int actionBarHeight = getResources().getDimensionPixelSize(tv.resourceId);
//        setMargins(mContent, 0, -actionBarHeight, 0, 0);
        mView.setVisibility(View.VISIBLE);
    }

}

