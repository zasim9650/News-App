package com.example.indianewsapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsRVAdaptor extends RecyclerView.Adapter<NewsRVAdaptor.MyViewHolder> {
    private ArrayList<Articales>articalesArrayList;
    private Context context;

    public NewsRVAdaptor(ArrayList<Articales> articalesArrayList, Context context) {
        this.articalesArrayList = articalesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsRVAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_rv_item,parent,false);
        return new NewsRVAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRVAdaptor.MyViewHolder holder, int position) {

        Articales articales = articalesArrayList.get(position);
        holder.sub_title_tv.setText(articales.getDescription());
        holder.title_tv.setText(articales.getTitle());
        Picasso.get().load(articales.getUrlToImage()).into(holder.news_iv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,NewsDetailActivity.class);
                i.putExtra("title",articales.getTitle());
                i.putExtra("content",articales.getContent());
                i.putExtra("des",articales.getDescription());
                i.putExtra("image",articales.getUrlToImage());
                i.putExtra("url",articales.getUrl());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articalesArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title_tv,sub_title_tv;
        private ImageView news_iv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title_tv = (TextView) itemView.findViewById(R.id.news_tv);
            sub_title_tv = (TextView) itemView.findViewById(R.id.sub_news_tv);
            news_iv = (ImageView) itemView.findViewById(R.id.new_iv);

        }
    }
}
