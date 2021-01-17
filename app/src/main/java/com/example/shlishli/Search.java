package com.example.shlishli;

import com.google.gson.annotations.SerializedName;

public class Search{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}
}