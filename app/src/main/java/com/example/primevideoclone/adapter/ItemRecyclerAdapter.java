package com.example.primevideoclone.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.primevideoclone.MovieDetails;
import com.example.primevideoclone.R;
import com.example.primevideoclone.model.CategoryItemList;

import java.util.List;

public class ItemRecyclerAdapter  extends RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder> {

    Context context;
    List<CategoryItemList> categoryItemList;

    public ItemRecyclerAdapter(Context context, List<CategoryItemList> categoryItemList) {
        this.context = context;
        this.categoryItemList = categoryItemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.cat_recycler_row_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        Glide.with(context).load(categoryItemList.get(position).getImageUrl()).into(holder.itemImage);

        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetails.class);
                intent.putExtra("movieId",categoryItemList.get(holder.getAdapterPosition()).getId());
                intent.putExtra("movieName",categoryItemList.get(holder.getAdapterPosition()).getMovieName());
                intent.putExtra("movieImageUrl",categoryItemList.get(holder.getAdapterPosition()).getImageUrl());
                intent.putExtra("movieFile",categoryItemList.get(holder.getAdapterPosition()).getFileUri());
                context.startActivity(intent);

//                Toast.makeText(context,""+categoryItemList.get(holder.getAdapterPosition()).getMovieName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    

    @Override
    public int getItemCount() {
        return  categoryItemList.size();
    }

    public  static  final class ItemViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage ;


        public  ItemViewHolder(@NonNull View itemView){
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
        }

    }

}
