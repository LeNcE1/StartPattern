package com.lence.startpattern.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.ui.selectionScreen.SelectionScreenFragment;
import com.lence.startpattern.ui.service.ServiceActivity;
import com.lence.startpattern.ui.sessionHistory.SessionHistoryFragment;
import com.lence.startpattern.utils.ChangeStyle;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        TextView label = (TextView) findViewById(R.id.label);
        label.setText("START");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ChangeStyle.whiteColor(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.online_record) {
            if (!this.getClass().equals(ServiceActivity.class)) {

                SelectionScreenFragment fragment = new SelectionScreenFragment();
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                //BackStackTools.clearStack(fm);
                FragmentTransaction ft = fm.beginTransaction();

                ft.replace(R.id.content, fragment, "SelectionScreen");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack("stack");
                ft.commit();
            }
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
}
