package com.lence.startpattern.ui.doctor.review;

import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.model.DoctorReviewsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DoctorReviewAdapter extends RecyclerView.Adapter<DoctorReviewAdapter.RibotViewHolder> {

    private List<DoctorReviewsModel> mRibots = new ArrayList<>();
    DoctorReviewPresenter mPresenter;
    SharedPreferences user;

    public DoctorReviewAdapter(List<DoctorReviewsModel> posts, DoctorReviewPresenter presenter) {
        mRibots = posts;
        mPresenter = presenter;
    }

    @Override
    public RibotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctor_review_item, parent, false);


        return new RibotViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final RibotViewHolder holder, final int position) {
        holder.mName.setText(mRibots.get(position).getName());
        holder.mReview.setText(mRibots.get(position).getText());
        holder.mRatingBar.setRating(mRibots.get(position).getRate());
        // TODO: 26.02.2018 исправить верстку карточки отзыва 

    }

    @Override
    public int getItemCount() {
        return mRibots.size();
    }

    class RibotViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.ratingBar)
        RatingBar mRatingBar;
        @BindView(R.id.count_review)
        TextView mCountReview;
        @BindView(R.id.review)
        TextView mReview;

        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
