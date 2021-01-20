package com.example.shlishli.dataModels;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductPlusMerchant {

    @Nullable
    @SerializedName("merchantDetails")
    private List<MerchantDetails> merchants;

    @SerializedName("product")
    private Product product;


    @Nullable
    public List<MerchantDetails> getMerchants() {
        return merchants;
    }

    public void setMerchants(@Nullable List<MerchantDetails> merchants) {
        this.merchants = merchants;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
