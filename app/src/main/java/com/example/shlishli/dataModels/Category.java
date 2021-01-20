package com.example.shlishli.dataModels;

import com.google.gson.annotations.SerializedName;

public class Category{

	@SerializedName("imageUrl")
	private String imageUrl;

	@SerializedName("name")
	private String name;

	@SerializedName("categoryId")
	private int categoryId;

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCategoryId(int categoryId){
		this.categoryId = categoryId;
	}

	public int getCategoryId(){
		return categoryId;
	}
}