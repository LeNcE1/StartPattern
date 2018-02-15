package com.lence.startpattern.ui.associate;

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
import com.lence.startpattern.SingletonStorage;
import com.lence.startpattern.model.AssociateModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AssociateAdapter extends RecyclerView.Adapter<AssociateAdapter.RibotViewHolder> {


    private List<AssociateModel> mRibots = new ArrayList<>();
    AssociatePresenter pr;
    SharedPreferences user;
    int pag = 20;
    String pod;
    String m;
    Context mContext;

    public AssociateAdapter(List<AssociateModel> posts, AssociatePresenter presenter, Context context) {
        mRibots = posts;
        pr = presenter;
        mContext = context;
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

    @SuppressLint("SetTextI18n")
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



        holder.name.setText(mRibots.get(position).getName()+" "+mRibots.get(position).getSurname()+" "+mRibots.get(position).getSecondname());
        holder.spec.setText(String.valueOf(mRibots.get(position).getDescription()));
        Picasso.with(mContext).load(mRibots.get(position).getImage()).into(holder.avatar);

           // holder.ratingBar.setRating(mRibots.get(position).get);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingletonStorage.getInstance().setAssociateName(mRibots.get(position).getName()+" "+mRibots.get(position).getSurname()+" "+mRibots.get(position).getSecondname());
                SingletonStorage.getInstance().setAssociateId(mRibots.get(position).getId());
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
