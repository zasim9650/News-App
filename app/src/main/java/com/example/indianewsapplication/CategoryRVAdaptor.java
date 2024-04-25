package com.example.indianewsapplication;



import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRVAdaptor extends RecyclerView.Adapter<CategoryRVAdaptor.MyViewHolder> {
    ArrayList<CategoryRecyclerModal>categoryRecyclerModals;
    Context context;
    private CategoryClickInterface categoryClickInterface;

    public CategoryRVAdaptor(ArrayList<CategoryRecyclerModal> categoryRecyclerModals, Context context, CategoryClickInterface categoryClickInterface) {
        this.categoryRecyclerModals = categoryRecyclerModals;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public CategoryRVAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categories_rv_item,parent,false);
        return new CategoryRVAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRVAdaptor.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CategoryRecyclerModal categoryRecyclerModal = categoryRecyclerModals.get(position);
        holder.category_tv.setText(categoryRecyclerModal.getCategory());

        Picasso.get().load(categoryRecyclerModal.getCategoryImageUrl()).into(holder.category_iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickInterface.onCategoryClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryRecyclerModals.size();
    }
    public interface CategoryClickInterface{
        void onCategoryClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView category_tv;
        private ImageView category_iv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            category_tv = itemView.findViewById(R.id.tv_category);
            category_iv =itemView.findViewById(R.id.iv_category);
        }
    }
}
