package com.example.foodorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorder.model.Allmenu;

import java.util.List;

public class AllAdapter extends RecyclerView.Adapter<AllAdapter.AllAdapterViewHolder> {

    private Context context;
    private List<Allmenu> allmenuList;

    public AllAdapter(Context context, List<Allmenu> allmenuList) {
        this.context = context;
        this.allmenuList = allmenuList;
    }

    @NonNull
    @Override
    public AllAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_recycler_items,parent,false);
        return new AllAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllAdapterViewHolder holder, int position) {
        holder.all_name.setText(allmenuList.get(position).getName());
        holder.all_note.setText(allmenuList.get(position).getNote());
        holder.all_rating.setText(allmenuList.get(position).getRating());
        holder.all_deliverytime.setText(allmenuList.get(position).getDeliveryTime());
        holder.all_deliverycost.setText(allmenuList.get(position).getDeliveryCharges());
        holder.all_price.setText(allmenuList.get(position).getPrice());
        Glide.with(context).load(allmenuList.get(position).getImageUrl()).into(holder.allimage);
    }

    @Override
    public int getItemCount() {
        return allmenuList.size();
    }


    public static class AllAdapterViewHolder extends RecyclerView.ViewHolder{
        ImageView allimage;
        TextView all_name,all_note,all_rating,all_deliverytime,all_deliverycost,all_price;

        public AllAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            allimage = itemView.findViewById(R.id.all_image);
            all_name = itemView.findViewById(R.id.all_name);
            all_note = itemView.findViewById(R.id.all_note);
            all_rating = itemView.findViewById(R.id.all_rating);
            all_deliverytime = itemView.findViewById(R.id.all_deliverytime);
            all_deliverycost = itemView.findViewById(R.id.all_deliverycost);
            all_price = itemView.findViewById(R.id.all_price);
        }


    }
}
