package com.example.shlishli.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.example.shlishli.R;
import com.example.shlishli.adapters.MerchantAdapter;
import com.example.shlishli.adapters.SearchRecyclerAdapter;
import com.example.shlishli.dataModels.Merchant;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails extends AppCompatActivity {

    private RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        recyclerView = findViewById(R.id.rv_Merchants);

        List<Merchant> merchantList = new ArrayList<>();

        merchantList.add(new Merchant(Integer.toUnsignedLong(1), "akshtih", Integer.toUnsignedLong(5), Double.parseDouble("5000")));
        merchantList.add(new Merchant(Integer.toUnsignedLong(1), "akshtih", Integer.toUnsignedLong(5), Double.parseDouble("5000")));
        merchantList.add(new Merchant(Integer.toUnsignedLong(1), "akshtih", Integer.toUnsignedLong(5), Double.parseDouble("5000")));
        merchantList.add(new Merchant(Integer.toUnsignedLong(1), "akshtih", Integer.toUnsignedLong(5), Double.parseDouble("5000")));
        merchantList.add(new Merchant(Integer.toUnsignedLong(1), "akshtih", Integer.toUnsignedLong(5), Double.parseDouble("5000")));
        merchantList.add(new Merchant(Integer.toUnsignedLong(1), "akshtih", Integer.toUnsignedLong(5), Double.parseDouble("5000")));
        merchantList.add(new Merchant(Integer.toUnsignedLong(1), "akshtih", Integer.toUnsignedLong(5), Double.parseDouble("5000")));
        merchantList.add(new Merchant(Integer.toUnsignedLong(1), "akshtih", Integer.toUnsignedLong(5), Double.parseDouble("5000")));
        merchantList.add(new Merchant(Integer.toUnsignedLong(1), "akshtih", Integer.toUnsignedLong(5), Double.parseDouble("5000")));
        merchantList.add(new Merchant(Integer.toUnsignedLong(1), "akshtih", Integer.toUnsignedLong(5), Double.parseDouble("5000")));
        merchantList.add(new Merchant(Integer.toUnsignedLong(1), "akshtih", Integer.toUnsignedLong(5), Double.parseDouble("5000")));



        MerchantAdapter merchantAdapter=new MerchantAdapter(merchantList);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProductDetails.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setAdapter(merchantAdapter);
    }
}