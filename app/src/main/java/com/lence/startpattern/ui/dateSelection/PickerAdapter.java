package com.lence.startpattern.ui.dateSelection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lence.startpattern.R;

import java.util.ArrayList;


public class PickerAdapter extends RecyclerView.Adapter<PickerAdapter.TextVH> {

    private Context context;
    ArrayList<String> dataList;
    private RecyclerView mRecyclerView;

    public PickerAdapter(Context context, ArrayList<String> dataList, RecyclerView recyclerView) {
        this.context = context;
        this.mRecyclerView = recyclerView;
        if(!dataList.get(0).equals("00:00")) {
            dataList.add(0, "00:00");
            dataList.add("00:00");
        }
        this.dataList = dataList;
    }

    @Override
    public TextVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.picker_item_layout, parent, false);
        return new PickerAdapter.TextVH(view);
    }

    @Override
    public void onBindViewHolder(TextVH holder, final int position) {
        TextVH textVH = holder;
        if (position >= 1 && position < dataList.size()-1) {
            textVH.pickerTxt.setText(dataList.get(position).toString());
            textVH.pickerTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mRecyclerView != null) {
                        mRecyclerView.smoothScrollToPosition(position);
                    }
                }
            });
        }
        else {
            textVH.pickerTxt.setText("     ");
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void swapData(ArrayList<String> newData) {
        dataList = newData;
        notifyDataSetChanged();
    }

    class TextVH extends RecyclerView.ViewHolder {
        TextView pickerTxt;

        public TextVH(View itemView) {
            super(itemView);
            pickerTxt = (TextView) itemView.findViewById(R.id.picker_item);
        }
    }
}