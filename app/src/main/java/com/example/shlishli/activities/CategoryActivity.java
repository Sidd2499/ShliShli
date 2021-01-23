package com.example.shlishli.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shlishli.R;
import com.example.shlishli.adapters.CategoryAdapter;
import com.example.shlishli.adapters.SearchRecyclerAdapter;
import com.example.shlishli.adapters.TopProductsAdapter;
import com.example.shlishli.apiCalls.IApiCalls;
import com.example.shlishli.dataModels.Product;
import com.example.shlishli.dataModels.ProductWithPrice;
import com.example.shlishli.dataModels.ResponseTemplate;
import com.example.shlishli.retrofit.networkManager.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView categoryImageView;
    private TextView categorytextView;
    private List<ProductWithPrice> products= new ArrayList<>();
    private int resid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categorytextView = findViewById(R.id.tv_category_name);
        categoryImageView = findViewById(R.id.iv_categoryImage);


        resid=getIntent().getIntExtra("id",1);

        categorytextView.setText(getIntent().getStringExtra("textcategory").toString());
        if(resid!=1)
        categoryImageView.setImageResource(resid);

         getCategoryDetails();


    }

    private void getCategoryDetails() {

        recyclerView=(RecyclerView)findViewById(R.id.rv_categoryProducts);

        Retrofit retrofitBuilder = RetrofitBuilder.getInstance();
        IApiCalls iApiCalls = retrofitBuilder.create(IApiCalls.class);


        int categoryId=getIntent().getIntExtra("categoryId",0);

        Call<ResponseTemplate> responses = iApiCalls.getProductsBasedOnCategoryId(categoryId);

        responses.enqueue(new Callback<ResponseTemplate>() {
            @Override
            public void onResponse(Call<ResponseTemplate> call, Response<ResponseTemplate> response) {

                System.out.println(response.body());

                if(response.body().getData().size()>0){
                    for (ProductWithPrice p:response.body().getData()
                    ) {
                        products.add(p);
                    }
                }

                CategoryAdapter categoryAdapter=new CategoryAdapter(products);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(categoryAdapter);
                categoryAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseTemplate> call, Throwable t) {


            }

        });

    }
}