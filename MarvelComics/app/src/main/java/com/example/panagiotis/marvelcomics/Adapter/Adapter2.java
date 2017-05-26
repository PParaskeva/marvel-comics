package com.example.panagiotis.marvelcomics.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.panagiotis.marvelcomics.Fragments.DetailFragment;
import com.example.panagiotis.marvelcomics.MainActivity;
import com.example.panagiotis.marvelcomics.R;
import com.example.panagiotis.marvelcomics.Realm.ComicsRealm;
import com.example.panagiotis.marvelcomics.utils.ItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolder> {

    Context context;
    int row;
    RealmResults<ComicsRealm> results;
    MainActivity mainActivity;

    public Adapter2(Context context, int row, RealmResults<ComicsRealm> results,MainActivity mainActivity) {
        this.context = context;
        this.row = row;
        this.results = results;
        this.mainActivity=mainActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(row,parent,false);
        return new Adapter2.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ComicName.setText(results.get(position).getName()+"\n"+results.get(position).getPrice()+"\n");

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick){
                    mainActivity.fragmentTransfer(new DetailFragment(),results.get(position).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.imageView2)
        ImageView ComicImage;
        @BindView(R.id.textView)
        TextView ComicName;
        @BindView(R.id.relative_layout)
        RelativeLayout relativeLayout;

        private ItemClickListener itemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            ComicImage.setVisibility(View.GONE);
            relativeLayout.setOnClickListener(this);
        }
        public void setClickListener(ItemClickListener itemClickListener){
            this.itemClickListener=itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getPosition(),false);
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view, getPosition(), true);
            return true;
        }
    }
}
