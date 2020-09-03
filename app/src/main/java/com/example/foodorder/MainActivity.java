package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodorder.Adapter.AllAdapter;
import com.example.foodorder.Adapter.PopularAdapter;
import com.example.foodorder.Adapter.RecommendAdapter;
import com.example.foodorder.model.Allmenu;
import com.example.foodorder.model.FoodData;
import com.example.foodorder.model.Popular;
import com.example.foodorder.model.Recommended;
import com.example.foodorder.retrofit.ApiInterface;
import com.example.foodorder.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    RecyclerView popularRecyclerView;
    RecyclerView recommendRecyclerView;
    RecyclerView allRecyclerView;

    PopularAdapter popularAdapter;
    RecommendAdapter recommendAdapter;
    AllAdapter allAdapter;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class); //取得連線基底

        Call<List<FoodData>> call = apiInterface.getAlldata();  //建立連線CALL

        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {
                //連線成功
                List<FoodData> foodDataList = response.body();
                getPopularData(foodDataList.get(0).getPopular());
                getRecommendData(foodDataList.get(0).getRecommended());
                getAllData(foodDataList.get(0).getAllmenu());
            }

            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {
                //連線失敗
                Toast.makeText(MainActivity.this,"Server is not response",Toast.LENGTH_SHORT).show();
            }
        });
        ToSearch();


    }

    private void getPopularData(List<Popular> popularList){
        popularRecyclerView =findViewById(R.id.popular_recycler);
        popularAdapter = new PopularAdapter(this,popularList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(popularAdapter);
    }

    private void getRecommendData(List<Recommended> recommendedList){
        recommendRecyclerView =findViewById(R.id.recommended_recycler);
        recommendAdapter = new RecommendAdapter(this,recommendedList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recommendRecyclerView.setLayoutManager(layoutManager);
        recommendRecyclerView.setAdapter(recommendAdapter);
    }

    private void getAllData(List<Allmenu> allmenuList){
        allRecyclerView = findViewById(R.id.all_recycler);
        allAdapter = new AllAdapter(this,allmenuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        allRecyclerView.setAdapter(allAdapter);
        allRecyclerView.setLayoutManager(layoutManager);
    }

    private void ToSearch(){
        editText = findViewById(R.id.MainSearch);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                getApplicationContext().startActivity(intent);
            }
        });
    }

    public void ToCartView(View view) {
        Intent intent = new Intent(getApplicationContext(),CartActivity.class);
        getApplicationContext().startActivity(intent);
    }


}