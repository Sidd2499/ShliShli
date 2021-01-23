package com.example.shlishli.dataModels;

import com.google.gson.annotations.SerializedName;

public class AddToCartModel{


	@SerializedName("cartId")
	private Long cartId;

	@SerializedName("quantity")
	private String quantity;

	@SerializedName("customerId")
	private int customerId;

	@SerializedName("inventoryId")
	private int inventoryId;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public void setQuantity(String quantity){
		this.quantity = quantity;
	}

	public String getQuantity(){
		return quantity;
	}

	public void setCustomerId(int customerId){
		this.customerId = customerId;
	}

	public int getCustomerId(){
		return customerId;
	}

	public void setInventoryId(int inventoryId){
		this.inventoryId = inventoryId;
	}

	public int getInventoryId(){
		return inventoryId;
	}
}