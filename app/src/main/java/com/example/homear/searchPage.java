package com.example.homear;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class searchPage extends AppCompatActivity {

    private RecyclerView productsRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);


        productsRecyclerView=findViewById(R.id.productsRecyclerView);

    }
}