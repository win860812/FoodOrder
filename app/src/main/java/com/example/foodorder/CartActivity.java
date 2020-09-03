package com.example.foodorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodorder.Adapter.CartAdapter;
import com.example.foodorder.Adapter.PopularAdapter;
import com.example.foodorder.model.Allmenu;
import com.example.foodorder.model.Popular;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CartActivity extends AppCompatActivity {

    Allmenu allmenu = new Allmenu();
    List<Allmenu> cartList = new ArrayList<>();
    RecyclerView cartRecyclerView;
    CartAdapter cartAdapter;
    MyApplication myApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        myApp = (MyApplication)getApplication();

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String imageUrl = intent.getStringExtra("imageUrl");

        if(myApp.getCartList().size() !=0)
        {
            cartList = myApp.getCartList();
        }
        allmenu.setName(name);
        allmenu.setPrice(price);
        allmenu.setImageUrl(imageUrl);
        cartList.add(allmenu);
        myApp.setCartList(cartList);
        getCartData(cartList);


    }


    private void getCartData(List<Allmenu> cartList) {
        cartRecyclerView = findViewById(R.id.cart_recycler);
        cartAdapter = new CartAdapter(this, cartList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cartRecyclerView.setLayoutManager(layoutManager);
        cartRecyclerView.setAdapter(cartAdapter);
    }


}