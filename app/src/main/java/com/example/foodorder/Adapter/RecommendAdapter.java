package com.example.foodorder.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorder.FoodDetails;
import com.example.foodorder.R;
import com.example.foodorder.model.Recommended;

import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendAdapterViewHolder> {

    private Context context;
    private List<Recommended> recommendedList;

    public RecommendAdapter(Context context, List<Recommended> recommendedList) {
        this.context = context;
        this.recommendedList = recommendedList;
    }

    @NonNull
    @Override
    public RecommendAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.recommend_recycler_items,parent,false);
        return new RecommendAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendAdapterViewHolder holder, final int position) {
        holder.recommendname.setText(recommendedList.get(position).getName());
        holder.recommendrating.setText(recommendedList.get(position).getRating());
        holder.recommendprice.setText(recommendedList.get(position).getPrice());
        holder.recommenddeliverytime.setText(recommendedList.get(position).getDeliveryTime());
        holder.recommenddeliveryprice.setText(recommendedList.get(position).getDeliveryCharges());
        Glide.with(context).load(recommendedList.get(position).getImageUrl()).into(holder.recommendImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(context, FoodDetails.class);
                i.putExtra("name",recommendedList.get(position).getName());
                i.putExtra("rating",recommendedList.get(position).getRating());
                i.putExtra("price",recommendedList.get(position).getPrice());
                i.putExtra("desc",recommendedList.get(position).getNote());
                i.putExtra("image",recommendedList.get(position).getImageUrl());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recommendedList.size();
    }

    public static  class RecommendAdapterViewHolder extends RecyclerView.ViewHolder{
        ImageView recommendImage;
        TextView recommendname,recommendprice,recommendrating,recommenddeliverytime,recommenddeliveryprice;

        public RecommendAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            recommendImage = itemView.findViewById(R.id.recommend_image);
            recommendname = itemView.findViewById(R.id.recommend_name);
            recommendprice = itemView.findViewById(R.id.recommend_price);
            recommendrating = itemView.findViewById(R.id.recommend_rating);
            recommenddeliverytime = itemView.findViewById(R.id.recommend_delivery_time);
            recommenddeliveryprice = itemView.findViewById(R.id.recommend_delivery);
        }
    }



}
