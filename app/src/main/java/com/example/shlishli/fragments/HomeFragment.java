package com.example.shlishli.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shlishli.MainActivity;
import com.example.shlishli.R;
import com.example.shlishli.activities.CategoryActivity;
import com.example.shlishli.activities.ProductDetails;
import com.example.shlishli.adapters.MerchantAdapter;
import com.example.shlishli.adapters.TopProductsAdapter;
import com.example.shlishli.apiCalls.IApiCalls;
import com.example.shlishli.dataModels.Product;
import com.example.shlishli.retrofit.networkManager.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private List<Product> topProducts = new ArrayList<>();

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    private CircleImageView categoryShoes;
    private CircleImageView categorySliders;
    private CircleImageView categoryCrocs;
    private CircleImageView categorySandals;
    private CircleImageView categoryFlipFlops;

    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home, container, false);
        categoryShoes=view.findViewById(R.id.iv_category_shoes);
        categoryCrocs=view.findViewById(R.id.iv_category_crocs);
        categorySliders=view.findViewById(R.id.iv_category_sliders);
        categorySandals=view.findViewById(R.id.iv_category_sandals);
        categoryFlipFlops=view.findViewById(R.id.iv_flipflops);
        recyclerView = view.findViewById(R.id.rv_top_product);



        categoryShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.getContext(), CategoryActivity.class);
                intent.putExtra("id",R.mipmap.shoesimg);
                intent.putExtra("textcategory","Shoes");
                intent.putExtra("categoryId",1);
                view.getContext().startActivity(intent);
            }
        });
        categoryCrocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.getContext(), CategoryActivity.class);
                intent.putExtra("id",R.mipmap.crocs);
                intent.putExtra("textcategory","Crocs");
                intent.putExtra("categoryId",4);
                view.getContext().startActivity(intent);
            }
        });
        categorySandals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.getContext(), CategoryActivity.class);
                intent.putExtra("id",R.mipmap.sandal);
                intent.putExtra("textcategory","Sandals");
                intent.putExtra("categoryId",3);
                view.getContext().startActivity(intent);
            }
        });

        categorySliders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.getContext(), CategoryActivity.class);
                intent.putExtra("id",R.mipmap.sliders);
                intent.putExtra("textcategory","Sliders");
                intent.putExtra("categoryId",2);
                view.getContext().startActivity(intent);
            }
        });
        categoryFlipFlops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.getContext(), CategoryActivity.class);
                intent.putExtra("id",R.mipmap.flipflops);
                intent.putExtra("textcategory","Flip Flops");
                intent.putExtra("categoryId",5);
                view.getContext().startActivity(intent);
            }
        });


        getTopProducts(view);

        return view;
    }



    void getTopProducts(View view){
        Retrofit retrofitBuilder = RetrofitBuilder.getInstance();
        IApiCalls iApiCalls = retrofitBuilder.create(IApiCalls.class);


        Call<List<Product>> responses = iApiCalls.getTopProducts();

        responses.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                topProducts = response.body();

                TopProductsAdapter topProductsAdapter=new TopProductsAdapter(topProducts);
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                recyclerView.setAdapter(topProductsAdapter);
                topProductsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}