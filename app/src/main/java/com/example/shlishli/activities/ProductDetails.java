package com.example.shlishli.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shlishli.MainActivity;
import com.example.shlishli.R;
import com.example.shlishli.adapters.MerchantAdapter;
import com.example.shlishli.adapters.SearchRecyclerAdapter;
import com.example.shlishli.apiCalls.IApiCalls;
import com.example.shlishli.dataModels.AddToCartModel;
import com.example.shlishli.dataModels.Merchant;
import com.example.shlishli.dataModels.MerchantDetails;
import com.example.shlishli.dataModels.Product;
import com.example.shlishli.dataModels.ProductPlusMerchant;
import com.example.shlishli.dataModels.ResponseTemplate2;
import com.example.shlishli.retrofit.networkManager.RetrofitBuilder;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductDetails extends AppCompatActivity {

    private RecyclerView recyclerView;

    private TextView tvProductMainName;
    private TextView tvProductPrice;
    private ImageView ivProductImage;
    private TextView tvProductSize;
    private TextView tvproductTitle;
    private Button btnAddToCart;
    private TextView inventoryTextView;

    private List<MerchantDetails> merchantList;
    private Product productDetails;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        tvProductMainName=(TextView)findViewById(R.id.tv_product_main_name);
        tvProductPrice=(TextView)findViewById(R.id.tv_product_main_price);
        tvProductSize=(TextView)findViewById(R.id.tv_productSize) ;
        tvproductTitle=(TextView)findViewById(R.id.tv_productInfo);
        btnAddToCart=(Button)findViewById(R.id.btn_add_to_cart);
        ivProductImage = (ImageView)findViewById(R.id.iv_product_main_img);
        recyclerView = findViewById(R.id.rv_Merchants);
        inventoryTextView = findViewById(R.id.tv_inventory_id);


        makeApiCall();


        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // make API call here and pass the cart Item

                AddToCartModel cartModel = new AddToCartModel();
                // get customer Id from shared preferences

                SharedPreferences sharedPreferences = getSharedPreferences("shlishli", Context.MODE_PRIVATE);
                int userId = sharedPreferences.getInt("userId", 1);

                cartModel.setCustomerId(userId);
                cartModel.setInventoryId(Integer.valueOf(inventoryTextView.getText().toString()));
                cartModel.setQuantity(String.valueOf(1));


                if(FirebaseAuth.getInstance().getCurrentUser() != null)
                    addToCartApiCall(cartModel);
                else
                    Toast.makeText(ProductDetails.this, "Please Login to continue", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void addToCartApiCall(AddToCartModel addToCartModel){
        Retrofit retrofitBuilder = RetrofitBuilder.getInstance();
        IApiCalls iApiCalls = retrofitBuilder.create(IApiCalls.class);

        Call<AddToCartModel> responseFromApiCall = iApiCalls.addToCart(addToCartModel);

        responseFromApiCall.enqueue(new Callback<AddToCartModel>() {
            @Override
            public void onResponse(Call<AddToCartModel> call, Response<AddToCartModel> response) {
                // show toast

                Toast.makeText(ProductDetails.this, "Item Succesfully Added to Cart", Toast.LENGTH_SHORT).show();

                Intent goToMainActivity = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(goToMainActivity);
                finish();
            }

            @Override
            public void onFailure(Call<AddToCartModel> call, Throwable t) {
                Toast.makeText(ProductDetails.this, "Something Went Wrong, Try again after some time", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void makeApiCall(){


        Retrofit retrofitBuilder = RetrofitBuilder.getInstance();
        IApiCalls iApiCalls = retrofitBuilder.create(IApiCalls.class);


        Long productIdFromIntent = getIntent().getLongExtra("productId", 1);

        Call<ResponseTemplate2> responseFromApiCall = iApiCalls.getProductDetails(productIdFromIntent);

        responseFromApiCall.enqueue(new Callback<ResponseTemplate2>() {
            @Override
            public void onResponse(Call<ResponseTemplate2> call, Response<ResponseTemplate2> response) {

                merchantList = response.body().getData().getMerchants();
                productDetails = response.body().getData().getProduct();


                Log.d("testing", response.body().toString());
//

                String imageUrl = productDetails.getImageUrl();
                Glide.with(ProductDetails.this).load(imageUrl).into(ivProductImage);
                String productName = productDetails.getProductName();
                String price = String.valueOf(merchantList.get(0).getPrice());
                String size = productDetails.getSize();
                String description = productDetails.getDescription();
                String inventoryId = String.valueOf(merchantList.get(0).getInventoryItemId());


                tvProductMainName.setText(productName);
                tvProductPrice.setText("₹ "+price);
                inventoryTextView.setText(inventoryId);
                tvProductSize.setText("Size : "+size);
                tvproductTitle.setText("Description : "+description);


                MerchantAdapter merchantAdapter=new MerchantAdapter(merchantList);
                recyclerView.setLayoutManager(new LinearLayoutManager(ProductDetails.this, LinearLayoutManager.HORIZONTAL, true));
                recyclerView.setAdapter(merchantAdapter);



            }

            @Override
            public void onFailure(Call<ResponseTemplate2> call, Throwable t) {

            }
        });

        // list -> recycler adapter


        // set the product values to appropriate text views
    }








}