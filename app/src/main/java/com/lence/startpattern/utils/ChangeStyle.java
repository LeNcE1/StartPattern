package com.lence.startpattern.utils;


import android.app.Activity;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lence.startpattern.R;

public abstract class ChangeStyle {

    public static void whiteColor(Activity activity){
        View appBarLayout = activity.findViewById(R.id.appBar);
        appBarLayout.setBackgroundResource(R.color.white);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout.setElevation(8);
        }
        TextView label = (TextView) activity.findViewById(R.id.label);
        label.setVisibility(View.VISIBLE);
        ImageView logo = activity.findViewById(R.id.logo);
        logo.setVisibility(View.VISIBLE);
        ImageView arrowBack = activity.findViewById(R.id.arrowBack);
        arrowBack.setColorFilter(activity.getResources().getColor(R.color.colorAccent));
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(R.color.white);
        ToolbarColorizeHelper.colorizeToolbar(toolbar, activity.getResources().getColor(R.color.colorAccent), activity);
    }

    public static void blueColor(Activity activity){
        View appBarLayout = activity.findViewById(R.id.appBar);
        //appBarLayout.setBackgroundResource(Color.alpha(0));
        appBarLayout.setBackgroundResource(R.color.blue);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout.setElevation(0);
        }
        TextView label = (TextView) activity.findViewById(R.id.label);
        label.setVisibility(View.GONE);
        ImageView logo = activity.findViewById(R.id.logo);
        logo.setVisibility(View.GONE);
        ImageView arrowBack = activity.findViewById(R.id.arrowBack);
        arrowBack.setColorFilter(activity.getResources().getColor(R.color.white));
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(R.color.blue);
        ToolbarColorizeHelper.colorizeToolbar(toolbar, activity.getResources().getColor(R.color.white), activity);
    }
}
