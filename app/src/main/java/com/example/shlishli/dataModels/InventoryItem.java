package com.example.shlishli.dataModels;

import com.google.gson.annotations.SerializedName;

public class InventoryItem{

	@SerializedName("inventoryItemId")
	private int inventoryItemId;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("productId")
	private int productId;

	@SerializedName("merchantId")
	private int merchantId;

	@SerializedName("price")
	private int price;

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

	public void setProductId(int productId){
		this.productId = productId;
	}

	public int getProductId(){
		return productId;
	}

	public void setMerchantId(int merchantId){
		this.merchantId = merchantId;
	}

	public int getMerchantId(){
		return merchantId;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}
}