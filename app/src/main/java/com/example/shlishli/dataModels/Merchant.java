package com.example.shlishli.dataModels;

import com.google.gson.annotations.SerializedName;

public class Merchant {

    @SerializedName("merchantId")
    private Long merchantId;

    @SerializedName("name")
    private String name;

    @SerializedName("rating")
    private Long rating;

    @SerializedName("double")
    private Double price;


    public Merchant(Long merchantId, String name, Long rating, Double price){
        this. merchantId = merchantId;
        this.name = name;
        this.rating = rating;
        this.price = price;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
