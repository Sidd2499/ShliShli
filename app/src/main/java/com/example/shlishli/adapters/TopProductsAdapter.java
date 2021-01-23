package com.example.shlishli.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shlishli.R;
import com.example.shlishli.activities.ProductDetails;
import com.example.shlishli.dataModels.Product;

import java.util.List;

public class TopProductsAdapter extends RecyclerView.Adapter<TopProductsAdapter.ViewHolder> {

    private List<Product> products;

    public TopProductsAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_product_item, parent, false);
        return new TopProductsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Product product = products.get(position);
        Glide.with(holder.rootView).load(product.getImageUrl()).into(holder.imageView);


        holder.productPrice.setText("Size : "+product.getSize() +" | Color : " +product.getColor());
        holder.productName.setText(product.getProductName());



        holder.rootView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ProductDetails.class);


            intent.putExtra("productId", product.getProductId());

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View rootView;
        private ImageView imageView;
        private TextView productName;
        private TextView productPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            imageView = itemView.findViewById(R.id.iv_top_product);
            productName = itemView.findViewById(R.id.tv_top_product_name);
            productPrice = itemView.findViewById(R.id.tv_top_product_price);
        }
    }
}
