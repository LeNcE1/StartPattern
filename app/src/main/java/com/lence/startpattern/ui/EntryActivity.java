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
import com.lence.startpattern.ui.createRecord.CreateRecordFragment;
import com.lence.startpattern.ui.onlineRecord.OnlineRecordFragment;
import com.lence.startpattern.ui.selectionScreen.SelectionScreenFragment;
import com.lence.startpattern.ui.service.ServiceActivity;
import com.lence.startpattern.ui.sessionHistory.SessionHistoryFragment;

public class EntryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        // toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);
// TODO: 14.03.2018 подумать над выделением главного активити чтобы вынести запись на прием в отдельное активити 
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SelectionScreenFragment fragment = new SelectionScreenFragment();
        //getSupportFragmentManager().popBackStack();
        android.support.v4.app.FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment, "SelectionScreen");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //ft.addToBackStack("stack");
        ft.commit();

        ImageView arrowBack = findViewById(R.id.arrowBack);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }


    @Override
    public void onBackPressed() {
        // TODO: 13.03.2018 полностью переделать, убрать везде добавление в бекстек, добавить теги к фрагментам и обрабатывать нажатие назад по тегу 
        // TODO: 13.03.2018 https://github.com/DattaHujare/NavigationDrawer/blob/master/app/src/main/java/com/spokeinfotech/navigationdrawer/MainActivity.java
        android.support.v4.app.FragmentManager fragmentManager = this.getSupportFragmentManager();
        //Log.e("BackStackEntryCount", fragmentManager.);
        Fragment selectionScreen = fragmentManager.findFragmentByTag("SelectionScreen");
        Fragment createRecord = fragmentManager.findFragmentByTag("CreateRecord");
        Fragment onlineRecord = fragmentManager.findFragmentByTag("OnlineRecord");
        Fragment associateAllList = fragmentManager.findFragmentByTag("AssociateAllList");
        Fragment sessionHistory = fragmentManager.findFragmentByTag("SessionHistory");
        Fragment doctor = fragmentManager.findFragmentByTag("Doctor");
        Fragment procedureDoctor = fragmentManager.findFragmentByTag("Procedure");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (createRecord instanceof CreateRecordFragment && createRecord.isVisible()) {
            fragmentManager.beginTransaction()
                    .hide(createRecord)
                    .replace(R.id.content,selectionScreen,"SelectionScreen")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .show(selectionScreen)
                    .commit();
        } else if (onlineRecord instanceof OnlineRecordFragment && onlineRecord.isVisible()) {
            fragmentManager.beginTransaction()
                    .hide(onlineRecord)
                    .replace(R.id.content,createRecord,"CreateRecord")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .show(selectionScreen)
                    .commit();
//        } else if (doctor instanceof DoctorActivity && doctor.isVisible()) {
//            fragmentManager.beginTransaction()
//                    .hide(doctor)
//                    .replace(R.id.content,associateAllList,"AssociateAllList")
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                    .show(associateAllList)
//                    .commit();
//        } else if (procedureDoctor instanceof ProcedureFragment && procedureDoctor.isVisible()) {
//            fragmentManager.beginTransaction()
//                    .hide(procedureDoctor)
//                    .replace(R.id.content,new DoctorActivity(),"Doctor")
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                    //.show(doctor)
//                    .commit();
       }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.online_record) {
            if (!this.getClass().equals(ServiceActivity.class)) {

                startActivity(new Intent(this, EntryActivity.class));
            }
        } else if (id == R.id.doctor) {
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            // BackStackTools.clearStack(fm);
            AssociateAllListFragment fragment = new AssociateAllListFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content, fragment, "AssociateAllList");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack("stack");
            ft.commit();
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
