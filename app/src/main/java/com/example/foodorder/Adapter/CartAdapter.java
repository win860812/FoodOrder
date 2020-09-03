package com.example.foodorder.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorder.CartActivity;
import com.example.foodorder.R;
import com.example.foodorder.model.Allmenu;
import com.example.foodorder.model.Popular;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<Allmenu> cartList;
    NumberCallback numberCallback; //宣告介面

    public CartAdapter(Context context, List<Allmenu> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_recycler_items, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, int position) {
        holder.foodname.setText(cartList.get(position).getName());
        holder.foodprice.setText(cartList.get(position).getPrice());
        Glide.with(context).load(cartList.get(position).getImageUrl()).into(holder.foodimage);

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddition(holder);
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubtraction(holder);
            }
        });
    }


    public void setNumberCallback(NumberCallback callback){
        numberCallback=callback;
    }


    public interface NumberCallback {

        void numberaddLoad(int number, int price);

        void numbersubLoad(int number, int price);
    }


    @Override
    public int getItemCount() {
        return cartList.size();
    }


    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView add, remove, foodimage;
        TextView foodname, foodprice, foodamount;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            foodname = itemView.findViewById(R.id.food_name);
            foodprice = itemView.findViewById(R.id.food_price);
            foodamount = itemView.findViewById(R.id.amount);
            add = itemView.findViewById(R.id.add_image);
            remove = itemView.findViewById(R.id.remove_image);
            foodimage = itemView.findViewById(R.id.food_image);
        }
    }

    private synchronized void onAddition(CartViewHolder holder) {

        int i = toInt(holder.foodamount.getText().toString());
        holder.foodamount.setText("" + (i + 1));
        if (numberCallback != null) {
            numberCallback.numberaddLoad(toInt(holder.foodamount.getText().toString()) - i, toInt(holder.foodprice.getText().toString()));
        }}

    private synchronized void onSubtraction(CartViewHolder holder) {

        if (toInt(holder.foodamount.getText().toString()) > 0) {
            int i = toInt(holder.foodamount.getText().toString());


            holder.foodamount.setText("" + (i - 1));
            if (numberCallback != null) {
                numberCallback.numbersubLoad(i - toInt(holder.foodamount.getText().toString()), toInt(holder.foodprice.getText().toString()));
            }
        }}

    public int toInt(String tostring) {
        return Integer.parseInt(tostring);
    }





}
