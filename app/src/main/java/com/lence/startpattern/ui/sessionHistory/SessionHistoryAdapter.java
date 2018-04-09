package com.lence.startpattern.ui.sessionHistory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lence.startpattern.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SessionHistoryAdapter extends RecyclerView.Adapter<SessionHistoryAdapter.RibotViewHolder> {



    private List<Object> mRibots = new ArrayList<>();
    private SessionHistoryPresenter mPresenter;

    public SessionHistoryAdapter(List<Object> posts, SessionHistoryPresenter presenter) {
        mRibots = posts;
        mPresenter = presenter;
    }

    @Override
    public RibotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.session_history_item, parent, false);


        return new RibotViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final RibotViewHolder holder, final int position) {
//        if (mRibots.get(position).getClass().isAssignableFrom(ServicesModel.class)) {
//            final ServicesModel model = (ServicesModel) mRibots.get(position);
//            holder.name.setText(model.getName());
//            holder.price.setText(model.getPrice().toString());
//
//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    SingletonStorage.getInstance().setServicesId(model.getId());
//                    SingletonStorage.getInstance().setServicesDescription(model.getName());
//                    mPresenter.startProcedure();
//                }
//            });
//        } else if (mRibots.get(position).getClass().isAssignableFrom(AssociateServicesModel.class)) {
//            final AssociateServicesModel model = (AssociateServicesModel) mRibots.get(position);
//            holder.name.setText(model.getName());
//            holder.price.setText(model.getPrice().toString());
//
//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    SingletonStorage.getInstance().setServicesId(model.getId());
//                    SingletonStorage.getInstance().setServicesDescription(model.getName());
//                    mPresenter.startProcedure();
//                }
//            });
//        }
    }


    @Override
    public int getItemCount() {
        return mRibots.size();
    }


    class RibotViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.avatar)
        ImageView mAvatar;
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.spec)
        TextView mSpec;
        @BindView(R.id.price)
        TextView mPrice;
        @BindView(R.id.details)
        View mDetails;

        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
