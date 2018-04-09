package com.lence.startpattern.ui.associateAll;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.model.AssociateModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AssociateAllAdapter extends RecyclerView.Adapter<AssociateAllAdapter.RibotViewHolder> {


    private List<AssociateModel> mRibots = new ArrayList<>();
    private AssociateAllPresenter mPresenter;
    SharedPreferences user;
    Context mContext;

    public AssociateAllAdapter(List<AssociateModel> posts, AssociateAllPresenter presenter, Context context) {
        mRibots = posts;
        mPresenter = presenter;
        mContext = context;
    }

//    public AssociateAllAdapter(List<News> posts, NewsPresentr mPresenter, SharedPreferences user, String pod, String m) {
//        this.pod = pod;
//        this.mRibots = posts;
//        this.mPresenter = mPresenter;
//        this.user = user;
//        this.m = m;
//    }

//    public void addPosts(List<News> ribots) {
//        for(News i: ribots){
//            mRibots.add(i);
//        }
//    }

    @Override
    public RibotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.associate_item, parent, false);


        return new RibotViewHolder(itemView);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final RibotViewHolder holder, final int position) {

        holder.name.setText(mRibots.get(position).getSurname() + " " + mRibots.get(position).getName() + " " + mRibots.get(position).getSecondname());
        holder.spec.setText(String.valueOf(mRibots.get(position).getDescription()));
        Picasso.with(mContext)
                .load(mRibots.get(position).getImage())
                .resize(150, 150)
                .centerCrop()
                .into(holder.avatar);

        holder.ratingBar.setRating(mRibots.get(position).getRate());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.startDoctor(mRibots.get(position).getId(),
                        mRibots.get(position).getSurname() + " " + mRibots.get(position).getName() + " " + mRibots.get(position).getSecondname(),
                        String.valueOf(mRibots.get(position).getDescription()),
                        mRibots.get(position).getImage(),mRibots.get(position).getRate());
            }
        });


    }


    @Override
    public int getItemCount() {
        return mRibots.size();
    }


    class RibotViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.spec)
        TextView spec;
        @BindView(R.id.count_review)
        TextView count_review;

        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.arrowNext)
        ImageView arrowNext;
        @BindView(R.id.ratingBar)
        RatingBar ratingBar;

        @BindView(R.id.view)
        View mView;


        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
