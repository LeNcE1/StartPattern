package com.lence.startpattern.ui.procedure;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.SingletonStorage;
import com.lence.startpattern.model.AssociateServicesModel;
import com.lence.startpattern.model.ServicesModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProcedureAdapter extends RecyclerView.Adapter<ProcedureAdapter.RibotViewHolder> {

    private List<Object> mRibots;
    ProcedurePresenter mPresenter;

    public ProcedureAdapter(List<Object> posts, ProcedurePresenter presenter) {
        mRibots = posts;
        mPresenter = presenter;
    }

    @Override
    public RibotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.procedure_item, parent, false);
        return new RibotViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RibotViewHolder holder, final int position) {
        if (mRibots.get(position).getClass().isAssignableFrom(ServicesModel.class)) {
            final ServicesModel model = (ServicesModel) mRibots.get(position);
            holder.name.setText(model.getName());
            holder.price.setText(model.getPrice().toString());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SingletonStorage.getInstance().setServices(model.getId(), model.getName(), model.getPrice());
                    mPresenter.startProcedure();
                }
            });
        } else if (mRibots.get(position).getClass().isAssignableFrom(AssociateServicesModel.class)) {
            final AssociateServicesModel model = (AssociateServicesModel) mRibots.get(position);
            holder.name.setText(model.getName());
            holder.price.setText(model.getPrice().toString());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SingletonStorage.getInstance().setServices(model.getId(), model.getName(), model.getPrice());
                    mPresenter.startProcedure();
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return mRibots.size();
    }


    class RibotViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.type)
        TextView name;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.view)
        View mView;


        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
