package com.example.foodorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
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
    int num;
    TextView TotalPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        myApp = (MyApplication) getApplication();
        num = 0;
        TotalPrice = findViewById(R.id.TotalPrice);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String imageUrl = intent.getStringExtra("imageUrl");

        if (myApp.getCartList().size() != 0) {
            cartList = myApp.getCartList();
        }
        if(name !=null) {
            allmenu.setName(name);
            allmenu.setPrice(price);
            allmenu.setImageUrl(imageUrl);
            cartList.add(allmenu);
            myApp.setCartList(cartList);
        }
        getCartData(cartList);
        cartAdapter.setNumberCallback(numberCallback);
    }


    private void getCartData(List<Allmenu> cartList) {
        cartRecyclerView = findViewById(R.id.cart_recycler);
        cartAdapter = new CartAdapter(this, cartList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cartRecyclerView.setLayoutManager(layoutManager);
        cartRecyclerView.setAdapter(cartAdapter);
    }


    //利用介面來使Adapter新增或減少價格
    CartAdapter.NumberCallback numberCallback = new CartAdapter.NumberCallback() {
        @Override
        public void numberaddLoad(int number, int price) {
            num += (price * number);
            TotalPrice.setText(Html.fromHtml("合计:" + ("" + num)));
        }

        @Override
        public void numbersubLoad(int number, int price) {
            num -= (price * number);
            TotalPrice.setText(Html.fromHtml("合计:" + ("" + num)));
        }
    };


    public void backtoMain(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void resetcart(View view) {
        myApp.claerCartList();
        cartAdapter.notifyDataSetChanged();
    }
}