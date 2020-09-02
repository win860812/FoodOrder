package com.example.foodorder.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorder.FoodDetails;
import com.example.foodorder.R;
import com.example.foodorder.model.Allmenu;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchAdapterViewHolder> {

    private Context context;
    private List<Allmenu> allmenuList;
    private List<Allmenu> filterList;

    public SearchAdapter(Context context, List<Allmenu> allmenuList) {
        this.context = context;
        this.allmenuList = allmenuList;
        this.filterList = allmenuList;
    }

    @NonNull
    @Override
    public SearchAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_recycler_items,parent,false);
        return new SearchAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapterViewHolder holder, final int position) {
        holder.all_name.setText(filterList.get(position).getName());
        holder.all_note.setText(filterList.get(position).getNote());
        holder.all_rating.setText(filterList.get(position).getRating());
        holder.all_deliverytime.setText(filterList.get(position).getDeliveryTime());
        holder.all_deliverycost.setText(filterList.get(position).getDeliveryCharges());
        holder.all_price.setText(filterList.get(position).getPrice());
        Glide.with(context).load(filterList.get(position).getImageUrl()).into(holder.allimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(context, FoodDetails.class);
                i.putExtra("name",filterList.get(position).getName());
                i.putExtra("rating",filterList.get(position).getRating());
                i.putExtra("price",filterList.get(position).getPrice());
                i.putExtra("desc",filterList.get(position).getNote());
                i.putExtra("image",filterList.get(position).getImageUrl());
                context.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    public static class SearchAdapterViewHolder extends RecyclerView.ViewHolder{
        ImageView allimage;
        TextView all_name,all_note,all_rating,all_deliverytime,all_deliverycost,all_price;

        public SearchAdapterViewHolder(@NonNull View itemView) {
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

    public  Filter getFliter(){

        return new Filter() {


            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String Key = charSequence.toString(); //使用者輸入的搜尋字串
                if(Key.isEmpty())
                {
                    filterList = allmenuList;
                }
                else {
                    List<Allmenu> listFiltered = new ArrayList<>();
                    for(Allmenu row:allmenuList){
                        if(row.getName().toLowerCase().contains(Key.toLowerCase())){
                            listFiltered.add(row);
                        }
                    }
                    filterList = listFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterList = (List<Allmenu>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
