package com.lence.startpattern.service;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lence.startpattern.R;

import java.util.ArrayList;

public class ServiceAdapter extends BaseAdapter {
    private Context mContext;
ArrayList<String> mList;

    public ServiceAdapter(Context c,ArrayList<String> list) {
        mContext = c;
        mList=list;

    }

    public int getCount() {
        return mList.size();
    }

    public Object getItem(int position) {
        return mList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View grid;

        if (convertView == null) {
            grid = new View(mContext);
            //LayoutInflater inflater = getLayoutInflater();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            grid = inflater.inflate(R.layout.service_item, parent, false);
        } else {
            grid = (View) convertView;
        }

        //ImageView imageView = (ImageView) grid.findViewById(R.id.imagepart);
        TextView textView = (TextView) grid.findViewById(R.id.serviceTitle);
       // imageView.setImageResource(mThumbIds[position]);
        textView.setText(mList.get(position));

        return grid;
    }


}
