package com.example.shlishli.dataModels;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class ProductWithPrice {

    @SerializedName("product")
    private Product product;

    @Nullable
    @SerializedName("price")
    private Integer price;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
