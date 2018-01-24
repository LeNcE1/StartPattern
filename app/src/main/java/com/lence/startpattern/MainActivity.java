package com.lence.startpattern;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.lence.startpattern.doctor.DoctorFragment;
import com.lence.startpattern.service.ServiceFragment;

import java.security.Provider;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        ServiceFragment fragment = new ServiceFragment();
        android.support.v4.app.FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack("stack");
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
        android.support.v4.app.FragmentManager fragmentManager = this.getSupportFragmentManager();
        // Log.e("BackStackEntryCount", String.valueOf(fragmentManager.getBackStackEntryCount()));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (fragmentManager.getBackStackEntryCount() > 1) {
                super.onBackPressed();
            }
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
// TODO: 24.01.2018 fix backstack 
        if (id == R.id.online_record) {
            if (!this.getClass().equals(ServiceFragment.class)) {
                TextView label = (TextView) findViewById(R.id.label);
                label.setVisibility(View.VISIBLE);
                View appBarLayout = findViewById(R.id.appBar);
                appBarLayout.setBackgroundResource(R.color.white);
                ImageView logo = findViewById(R.id.logo);
                logo.setVisibility(View.VISIBLE);
                ImageView arrowBack = findViewById(R.id.arrowBack);
                arrowBack.setColorFilter(getResources().getColor(R.color.colorAccent));
                View toolbar = findViewById(R.id.toolbar);
                toolbar.setBackgroundResource(R.color.white);
                ServiceFragment fragment = new ServiceFragment();
                android.support.v4.app.FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack("stack");
                ft.commit();
            }
        } else if (id == R.id.doctor) {
            TextView label = (TextView) findViewById(R.id.label);
            label.setVisibility(View.GONE);
            View appBarLayout = findViewById(R.id.appBar);
            appBarLayout.setBackgroundResource(R.color.blue);
            ImageView logo = findViewById(R.id.logo);
            logo.setVisibility(View.GONE);
            ImageView arrowBack = findViewById(R.id.arrowBack);
            arrowBack.setColorFilter(getResources().getColor(R.color.white));
            View toolbar = findViewById(R.id.toolbar);
            toolbar.setBackgroundResource(R.color.blue);
            DoctorFragment fragment = new DoctorFragment();
            android.support.v4.app.FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content, fragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack("stack");
            ft.commit();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
