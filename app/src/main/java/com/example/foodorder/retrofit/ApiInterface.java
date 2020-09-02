package com.example.foodorder.retrofit;

import com.example.foodorder.model.FoodData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("fooddata.json") // 設置一個GET連線，路徑為fooddata.json
    Call<List<FoodData>> getAlldata(); // 取得的回傳資料用FoodData物件接收，連線名稱取為getAlldata


}
