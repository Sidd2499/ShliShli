package com.example.shlishli.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.shlishli.R;
import com.example.shlishli.adapters.CategoryAdapter;
import com.example.shlishli.adapters.SearchRecyclerAdapter;
import com.example.shlishli.dataModels.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recyclerView=(RecyclerView)findViewById(R.id.rv_categoryProducts);


        List<Product> products = new ArrayList<>();

        products.add(new Product());
        products.add(new Product());
        products.add(new Product());products.add(new Product());products.add(new Product());products.add(new Product());products.add(new Product());






        CategoryAdapter categoryAdapter=new CategoryAdapter(products);
        recyclerView.setLayoutManager(new LinearLayoutManager(CategoryActivity.this));
        recyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
    }
}