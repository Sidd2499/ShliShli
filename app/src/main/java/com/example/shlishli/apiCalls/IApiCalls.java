package com.example.shlishli.apiCalls;

import com.example.shlishli.dataModels.AddToCartModel;
import com.example.shlishli.dataModels.CartItem;
import com.example.shlishli.dataModels.Category;
import com.example.shlishli.dataModels.Customer;
import com.example.shlishli.dataModels.OrderItem;
import com.example.shlishli.dataModels.Product;
import com.example.shlishli.dataModels.ResponseTemplate;
import com.example.shlishli.dataModels.ResponseTemplate2;
import com.example.shlishli.dataModels.ResponseTemplateCartItems;
import com.example.shlishli.dataModels.Search;
import com.example.shlishli.dataModels.UserActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IApiCalls {

    @GET("findByName/{name}")
    Call<List<Search>> getPosts(@Path("name") String name);

    @POST("customers/add")
    Call<Customer> addCustomer(@Body Customer customer);

    @GET("customers/getCustomerDetailsByFirebaseUserId/{id}")
    Call<Customer> getUserDetailsByFirebaseUID(@Path("id") String id);

    @GET("products/getTopProducts")
    Call<List<Product>> getTopProducts();

    @GET("apiJunction/getProductsByCategoryId/{categoryId}")
    Call<ResponseTemplate> getProductsBasedOnCategoryId(@Path("categoryId") int id);


    @POST("userActivity/add")
    Call<Void> logUserActivity(@Body UserActivity userActivity);


    @GET("apiJunction/getProductDetails/{productId}")
    Call<ResponseTemplate2> getProductDetails(@Path("productId") Long productId);

    @GET("categories/getAllCategories")
    Call<List<Category>> getAllCategories();

    @GET("apiJunction/getCartDetails/{id}")
    Call<ResponseTemplateCartItems> getCartDetails(@Path("id") Long customerId);

    @POST("carts/add")
    Call<AddToCartModel> addToCart(@Body AddToCartModel cartModel);

    // order now to be implemented
    @POST("orders/add")
    Call<OrderItem> addOrder(@Body OrderItem orderItem);

    // my Orders
    @GET("orders/getOrderDetailsByCustomerId/{id}")
    Call<List<OrderItem>> getCustomerOrders(@Path("id") Long customerId);

    // delete cart item
    @DELETE("carts/delete/{cartItemId}")
    Call<Void> deleteCartItem(@Path("cartItemId") Long cartItemId);

    // increase or decrease cart item


}
