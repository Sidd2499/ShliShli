package com.example.shlishli.dataModels;

import com.google.gson.annotations.SerializedName;

public class OrderItem{

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("orderId")
	private int orderId;

	@SerializedName("customerId")
	private int customerId;

	@SerializedName("inventoryId")
	private int inventoryId;

	@SerializedName("orderedOn")
	private String orderedOn;

	@SerializedName("status")
	private String status;

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setOrderId(int orderId){
		this.orderId = orderId;
	}

	public int getOrderId(){
		return orderId;
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

	public void setOrderedOn(String orderedOn){
		this.orderedOn = orderedOn;
	}

	public String getOrderedOn(){
		return orderedOn;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}