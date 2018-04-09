package com.lence.startpattern.utils;


import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lence.startpattern.R;

public abstract class ChangeStyle {

    public static void whiteColor(Activity activity) {
        View appBarLayout = activity.findViewById(R.id.appBar);
        View content = activity.findViewById(R.id.content);
        setMargins(content, 0, 0, 0, 0);
        appBarLayout.setBackgroundResource(R.color.colorPrimary);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout.setElevation(0);
        }
        TextView label = (TextView) activity.findViewById(R.id.label);
        label.setVisibility(View.VISIBLE);
        ImageView logo = activity.findViewById(R.id.logo);
        logo.setVisibility(View.VISIBLE);
        ImageView arrowBack = activity.findViewById(R.id.arrowBack);
        arrowBack.setColorFilter(activity.getResources().getColor(R.color.colorAccent));
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(R.color.colorPrimary);
        ToolbarColorizeHelper.colorizeToolbar(toolbar, activity.getResources().getColor(R.color.colorAccent), activity);
    }

    public static void blueColor(Activity activity) {
        View appBarLayout = activity.findViewById(R.id.appBar);
        View content = activity.findViewById(R.id.content);
        TypedValue typedValue = new TypedValue();
        activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true);
        int actionBarHeight = activity.getResources().getDimensionPixelSize(typedValue.resourceId);
        setMargins(content,0,-actionBarHeight,0,0);
        appBarLayout.setBackgroundResource(Color.alpha(0));
        //appBarLayout.setBackgroundResource(R.color.blue);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout.setElevation(0.1f);
        }
        TextView label = (TextView) activity.findViewById(R.id.label);
        label.setVisibility(View.GONE);
        ImageView logo = activity.findViewById(R.id.logo);
        logo.setVisibility(View.GONE);
        ImageView arrowBack = activity.findViewById(R.id.arrowBack);
        arrowBack.setColorFilter(activity.getResources().getColor(R.color.white));
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(Color.alpha(0));
        ToolbarColorizeHelper.colorizeToolbar(toolbar, activity.getResources().getColor(R.color.white), activity);
    }

//    public static void upViewMargins(Context con, ViewGroup.LayoutParams params) {
//        final float scale = con.getResources().getDisplayMetrics().density;
//
//        // convert the DP into pixel
//        int pixel_top = (int) (-actionBarHeight );
//
//        ViewGroup.MarginLayoutParams s = (ViewGroup.MarginLayoutParams) params;
//        s.setMargins(0, pixel_top, 0, 0);
//    }

    public static void setMargins(View view, int left, int top, int right, int bot) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            layoutParams.setMargins(left, top, right, bot);
            view.requestLayout();
        }
    }
}
