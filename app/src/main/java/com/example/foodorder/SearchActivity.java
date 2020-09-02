package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodorder.Adapter.SearchAdapter;
import com.example.foodorder.model.Allmenu;
import com.example.foodorder.model.FoodData;
import com.example.foodorder.retrofit.ApiInterface;
import com.example.foodorder.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    EditText searchView;
    SearchAdapter searchAdapter;
    RecyclerView searchrecycler;
    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.MainSearch);

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class); //取得連線基底

        Call<List<FoodData>> call = apiInterface.getAlldata();  //建立連線CALL

        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {
                //連線成功
                List<FoodData> foodDataList = response.body();
                setUserRecycler(foodDataList.get(0).getAllmenu());
            }

            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {
                //連線失敗
                Toast.makeText(SearchActivity.this,"Server is not response",Toast.LENGTH_SHORT).show();
            }
        });
        search();

    }

    private void setUserRecycler(List<Allmenu> allmenuList) {
        searchrecycler = findViewById(R.id.search_recycler);
        searchAdapter = new SearchAdapter(this,allmenuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        searchrecycler.setLayoutManager(layoutManager);
        searchrecycler.setAdapter(searchAdapter);
    }


    private void search() {
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchAdapter.getFliter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}