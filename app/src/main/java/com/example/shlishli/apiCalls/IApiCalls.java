package com.example.shlishli.apiCalls;

import com.example.shlishli.dataModels.Category;
import com.example.shlishli.dataModels.Customer;
import com.example.shlishli.dataModels.Product;
import com.example.shlishli.dataModels.Search;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IApiCalls {

    @GET("findByName/{name}")
    Call<List<Search>> getPosts(@Path("name") String name);

    @POST("customers/add")
    Call<Customer> addCustomer(@Body Customer customer);

    @GET("customers/getCustomerDetailsByFirebaseUserId/{id}")
    Call<Customer> getUserDetailsByFirebaseUID(@Path("id") String id);

    @GET("apiJunction/getTopProducts")
    Call<List<Product>> getTopProducts();

    @GET("categories/getAllCategories")
    Call<List<Category>> getAllCategories();

    @GET("apiJuntion/getProductsByCategoryId/{categoryId}")
    Call<Category> getProductsOfCategory(@Path("categoryId") int id);



}
