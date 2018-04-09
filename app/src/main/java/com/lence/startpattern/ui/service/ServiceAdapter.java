package com.lence.startpattern.ui.service;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.model.SectionsModel;

import java.util.List;

public class ServiceAdapter extends BaseAdapter {
    private Context mContext;
    private List<SectionsModel> mList;

    ServiceAdapter(Context c, List<SectionsModel> list) {
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
        TextView serviceTitle = (TextView) grid.findViewById(R.id.serviceTitle);
       // imageView.setImageResource(mThumbIds[position]);
        serviceTitle.setText(mList.get(position).getName());
        TextView serviceCount = grid.findViewById(R.id.serviceCount);
        serviceCount.setText(String.valueOf(mList.get(position).getChildrens().size()));

        return grid;
    }


}
