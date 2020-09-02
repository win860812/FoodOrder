package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FoodDetails extends AppCompatActivity {

    String name,price,rating,imageUrl,note;
    ImageView imageView;
    TextView itemname,itemprice,itemrating,itemnote;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        init();
    }


    private void init(){
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        rating = intent.getStringExtra("rating");
        imageUrl = intent.getStringExtra("image");
        note = intent.getStringExtra("desc");

        itemname = findViewById(R.id.food_name);
        itemprice = findViewById(R.id.price);
        itemrating = findViewById(R.id.rating);
        itemnote = findViewById(R.id.foodnote);
        imageView = findViewById(R.id.food_image);
        ratingBar = findViewById(R.id.ratingBar);

        Glide.with(this).load(imageUrl).into(imageView);
        itemname.setText(name);
        itemprice.setText(price);
        itemrating.setText(rating);
        itemnote.setText(note);
        ratingBar.setRating(Float.parseFloat(rating));

    }
}