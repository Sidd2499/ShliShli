package com.example.shlishli.dataModels;

import com.google.gson.annotations.SerializedName;

public class MerchantDetails{

	@SerializedName("inventoryItemId")
	private int inventoryItemId;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("merchantId")
	private Long merchantId;

	@SerializedName("price")
	private Double price;

	@SerializedName("merchantName")
	private String merchantName;

	public void setInventoryItemId(int inventoryItemId){
		this.inventoryItemId = inventoryItemId;
	}

	public int getInventoryItemId(){
		return inventoryItemId;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setMerchantId(Long merchantId){
		this.merchantId = merchantId;
	}

	public Long getMerchantId(){
		return merchantId;
	}

	public void setPrice(Double price){
		this.price = price;
	}

	public Double getPrice(){
		return price;
	}

	public void setMerchantName(String merchantName){
		this.merchantName = merchantName;
	}

	public String getMerchantName(){
		return merchantName;
	}
}
