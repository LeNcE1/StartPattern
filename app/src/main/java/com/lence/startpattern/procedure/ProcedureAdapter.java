package com.lence.startpattern.procedure;

import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lence.startpattern.R;
import com.lence.startpattern.associate.AssociatePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProcedureAdapter extends RecyclerView.Adapter<ProcedureAdapter.RibotViewHolder> {


    private List<String> mRibots = new ArrayList<>();
    ProcedurePresenter pr;
    SharedPreferences user;
    int pag = 20;
    String pod;
    String m;

    public ProcedureAdapter(List<String> posts, ProcedurePresenter presenter) {
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
                .inflate(R.layout.procedure_item, parent, false);


        return new RibotViewHolder(itemView);
    }




    @Override
    public void onBindViewHolder(final RibotViewHolder holder, final int position) {



        final String example = mRibots.get(position);
        holder.name.setText(example);


        //holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pr.startProcedure();
//            }
//        });


    }


    @Override
    public int getItemCount() {
        return mRibots.size();
    }


    class RibotViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.type)
        TextView name;
        @BindView(R.id.price)
        TextView spec;

        @BindView(R.id.view)
        View mView;


        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
