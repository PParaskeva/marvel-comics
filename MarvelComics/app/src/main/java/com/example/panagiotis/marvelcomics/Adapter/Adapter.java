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
import com.example.panagiotis.marvelcomics.pojos.Example;
import com.example.panagiotis.marvelcomics.utils.ItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    int row;
    Context context;
    Example example;
    MainActivity mainActivity;

    public Adapter(int row, Context context, Example example,MainActivity mainActivity) {
        this.row = row;
        this.context = context;
        this.example = example;
        this.mainActivity=mainActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(row,parent,false);
        return new Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        System.out.println(example.getData().getResults().get(position).getTitle());
//        System.out.println(example
//                .getData()
//                .getResults()
//                .get(position)
//                .getThumbnail().getPath());
        holder.ComicName.setText(example.getData().getResults().get(position).getTitle()
        +"\n"+example.getData().getResults().get(position).getPrices().get(0).getPrice()+"\n");

//        Picasso.with(context)
//                .load(example
//                        .getData().getResults().get(position).get
//                .resize(50, 50)
//                .centerCrop()
//                .into(holder.ComicImage);
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick){
                    mainActivity.fragmentTransfer(new DetailFragment(),example.getData().getResults().get(position).getId().toString()+"/");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return example.getData().getResults().size();
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
