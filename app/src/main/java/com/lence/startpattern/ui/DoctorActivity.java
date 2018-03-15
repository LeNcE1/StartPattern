package com.lence.startpattern.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.lence.startpattern.R;
import com.lence.startpattern.ui.associateAll.AssociateAllListFragment;
import com.lence.startpattern.ui.doctor.DoctorFragment;
import com.lence.startpattern.ui.procedure.ProcedureFragment;
import com.lence.startpattern.ui.sessionHistory.SessionHistoryFragment;

public class DoctorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        AssociateAllListFragment fragment = new AssociateAllListFragment();
        android.support.v4.app.FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment, "SelectionScreen");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

        ImageView arrowBack = findViewById(R.id.arrowBack);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.online_record) {

            startActivity(new Intent(this, EntryActivity.class));

        } else if (id == R.id.doctor) {

            startActivity(new Intent(this, DoctorActivity.class));

        } else if (id == R.id.history) {
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
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
        android.support.v4.app.FragmentManager fragmentManager = this.getSupportFragmentManager();
        Fragment associateAllList = fragmentManager.findFragmentByTag("AssociateAllList");
        Fragment doctor = fragmentManager.findFragmentByTag("Doctor");
        Fragment procedureDoctor = fragmentManager.findFragmentByTag("Procedure");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (associateAllList instanceof AssociateAllListFragment && associateAllList.isVisible()) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (doctor instanceof DoctorFragment && doctor.isVisible()) {
            fragmentManager.beginTransaction()
                    .hide(doctor)
                    .replace(R.id.content, new AssociateAllListFragment(), "AssociateAllList")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        } else if (procedureDoctor instanceof ProcedureFragment && procedureDoctor.isVisible()) {
            fragmentManager.beginTransaction()
                    .hide(procedureDoctor)
                    .replace(R.id.content, new DoctorFragment(), "Doctor")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();

        }
        else startActivity(new Intent(this, MainActivity.class));
    }
}
