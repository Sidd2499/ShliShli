package com.example.shlishli.adapters;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shlishli.R;
import com.example.shlishli.activities.ProductDetails;
import com.example.shlishli.dataModels.ProductWithPrice;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<ProductWithPrice> productWithPrices;

    public CategoryAdapter(List<ProductWithPrice> products){
        this.productWithPrices = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductWithPrice product = productWithPrices.get(position);

        holder.productName.setText(product.getProduct().getProductName());
        holder.productPrice.setText("₹ "+product.getPrice().toString()+" | Size : "+product.getProduct().getSize());
        Glide.with(holder.rootView).load(product.getProduct().getImageUrl()).into(holder.productImage);
//        holder.productImage.setImageURI(Uri.parse(product.getProduct().getImageUrl()));

        holder.rootView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ProductDetails.class);

            intent.putExtra("productId", product.getProduct().getProductId());

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productWithPrices.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View rootView;

        private TextView productName;
        private TextView productPrice;
        private ImageView productImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.tv_itemName);
            productPrice = itemView.findViewById(R.id.tv_item_price);
            productImage = itemView.findViewById(R.id.iv_productImg);
            rootView = itemView;

        }
    }

}
