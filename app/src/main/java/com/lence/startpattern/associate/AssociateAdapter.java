package com.lence.startpattern.associate;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.lence.startpattern.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AssociateAdapter extends RecyclerView.Adapter<AssociateAdapter.RibotViewHolder> {


    private List<String> mRibots = new ArrayList<>();
    AssociatePresenter pr;
    SharedPreferences user;
    int pag = 20;
    String pod;
    String m;

    public AssociateAdapter(List<String> posts, AssociatePresenter presenter) {
        mRibots = posts;
        pr = presenter;
    }

//    public AssociateAdapter(List<News> posts, NewsPresentr pr, SharedPreferences user, String pod, String m) {
//        this.pod = pod;
//        this.mRibots = posts;
//        this.pr = pr;
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


    int doposition = 0;

    @Override
    public void onBindViewHolder(final RibotViewHolder holder, final int position) {
//        if(position == (pag-6)){
//            if(pod == null) {
//                if(m != null){
//                    Log.e("My post", "my post");
//                    holder.delete.setVisibility(View.VISIBLE);
//                    pr.loadNewsMy(user.getString("id", "1"), pag);
//                }
//                else {
//                    pr.loadNews(user.getString("id", "1"), pag);
//                }
//            }
//            else{
//                pr.loadNewspod(user.getString("id", "1"), pag);
//            }
//            pag+=20;
//        }
//        else{
//            if(m != null){
//                Log.e("My post", "my post");
//                holder.delete.setVisibility(View.VISIBLE);
//                holder.delete.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        pr.deletePost(mRibots.get(position).getPostId());
//                        pr.replase();
//
//                    }
//                });
//            }
//        }


        final String example = mRibots.get(position);
        holder.name.setText(example);
        if (position <= 5) {
            holder.ratingBar.setRating(position);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pr.startProcedure();
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
