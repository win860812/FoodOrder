package com.example.foodorder;

import android.app.Application;

import com.example.foodorder.model.Allmenu;

import java.util.ArrayList;
import java.util.List;

public class MyApplication  extends Application {
    private  List<Allmenu> cartList = new ArrayList<>();

    public void setCartList(List<Allmenu> cartList) {
        this.cartList = cartList;
    }

    public List<Allmenu> getCartList() {
        return cartList;
    }


    public List<Allmenu> claerCartList() {
        cartList.clear();
        return cartList;
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }




}
