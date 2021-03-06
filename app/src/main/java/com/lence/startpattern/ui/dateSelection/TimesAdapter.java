package com.lence.startpattern.ui.dateSelection;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lence.startpattern.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TimesAdapter extends RecyclerView.Adapter<TimesAdapter.RibotViewHolder> {



    private List<String> mRibots;
    private DateSelectionPresenter mPresenter;


    public TimesAdapter(ArrayList<String> posts, DateSelectionPresenter presenter) {
        mRibots = posts;
        mPresenter = presenter;
    }

    @Override
    public RibotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.freetime_item, parent, false);


        return new RibotViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RibotViewHolder holder, final int position) {


        final String example = String.valueOf(mRibots.get(position));
        holder.mPickerItem.setText(example);
    }

    @Override
    public int getItemCount() {
        return mRibots.size();
    }


    class RibotViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.picker_item)
        TextView mPickerItem;


        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
