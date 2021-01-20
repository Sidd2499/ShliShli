package com.example.shlishli.dataModels;

import com.google.gson.annotations.SerializedName;

public class Customer{


	@SerializedName("footSize")
	private String footSize;

	@SerializedName("firstName")
	private String firstName;

	@SerializedName("lastName")
	private String lastName;

	@SerializedName("address")
	private String address;

	@SerializedName("mobileNumber")
	private String mobileNumber;

	@SerializedName("imageUrl")
	private String imageUrl;

	@SerializedName("customerId")
	private int customerId;

	@SerializedName("firebaseCustomerId")
	private String firebaseCustomerId;

	@SerializedName("age")
	private int age;

	public void setFootSize(String footSize){
		this.footSize = footSize;
	}

	public String getFootSize(){
		return footSize;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setMobileNumber(String mobileNumber){
		this.mobileNumber = mobileNumber;
	}

	public String getMobileNumber(){
		return mobileNumber;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setCustomerId(int customerId){
		this.customerId = customerId;
	}

	public int getCustomerId(){
		return customerId;
	}

	public void setFirebaseCustomerId(String firebaseCustomerId){
		this.firebaseCustomerId = firebaseCustomerId;
	}

	public String getFirebaseCustomerId(){
		return firebaseCustomerId;
	}

	public void setAge(int age){
		this.age = age;
	}

	public int getAge(){
		return age;
	}
}