package com.example.shlishli.dataModels;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseTemplate2 {

    @SerializedName("data")
    private ProductPlusMerchant data;

    @SerializedName("success")
    private boolean success;

    @Nullable
    @SerializedName("error")
    private String error;

    public void setData(ProductPlusMerchant data) {
        this.data = data;
    }

    public ProductPlusMerchant getData() {
        return data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

}
