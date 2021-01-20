package com.example.shlishli.dataModels;

import com.google.gson.annotations.SerializedName;

public class UserActivity {

    @SerializedName("customerId")
    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
