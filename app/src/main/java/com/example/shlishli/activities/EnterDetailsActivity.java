package com.example.shlishli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shlishli.MainActivity;
import com.example.shlishli.R;
import com.example.shlishli.apiCalls.IApiCalls;
import com.example.shlishli.dataModels.Customer;
import com.example.shlishli.retrofit.networkManager.RetrofitBuilder;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EnterDetailsActivity extends AppCompatActivity {

    private EditText tvFirstName;
    private EditText tvLastName;
    private EditText tvMobileNumber;
    private EditText tvAge;
    private EditText tvFootSize;
    private EditText tvAddress;
    private Button btnAddDetails;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_enter_details);
        tvFirstName=(EditText)findViewById(R.id.tv_firstname);
        tvLastName=(EditText)findViewById(R.id.tv_lastname);
        tvMobileNumber=(EditText)findViewById(R.id.tv_mobileno);
        tvAge=(EditText)findViewById(R.id.tv_age);
        tvFootSize=(EditText)findViewById(R.id.tv_footsize);
        tvAddress=(EditText)findViewById(R.id.tv_address);
        btnAddDetails=(Button)findViewById(R.id.btn_enter_details);
        btnAddDetails.setOnClickListener(v -> {

            if(validateTextFields())
                addDetails();
            else
                Toast.makeText(EnterDetailsActivity.this, "Please Enter You Details", Toast.LENGTH_SHORT).show();

        });
    }

    private boolean validateTextFields(){
        if(TextUtils.isEmpty(tvFirstName.getText().toString()))
        {
            tvFirstName.setError("Enter First Name");
            return false;
        }
        if(TextUtils.isEmpty(tvLastName.getText().toString()))
        {
            tvLastName.setError("Enter Last Name");
            return false;
        }
        if(TextUtils.isEmpty(tvAddress.getText().toString()))
        {
            tvAddress.setError("Enter Address");
            return false;
        }
        if(TextUtils.isEmpty(tvAge.getText().toString()))
        {
            tvAge.setError("Enter Age");
            return false;
        }
        if(TextUtils.isEmpty(tvFootSize.getText().toString()))
        {
            tvFootSize.setError("Enter Foot Size");
            return false;
        }
        if(TextUtils.isEmpty(tvMobileNumber.getText().toString()))
        {
            tvMobileNumber.setError("Enter Mobile Number");
            return false;
        }

        return true;
    }

    private void addDetails() {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        IApiCalls iApiCalls = retrofit.create(IApiCalls.class);

       Customer customer = new Customer();
       customer.setFirstName(tvFirstName.getText().toString());
       customer.setLastName(tvLastName.getText().toString());
       customer.setAddress(tvAddress.getText().toString());
       customer.setAge(Integer.parseInt(tvAge.getText().toString()));
       customer.setFootSize(tvFootSize.getText().toString());
       customer.setMobileNumber(tvMobileNumber.getText().toString());
       customer.setFirebaseCustomerId(firebaseAuth.getCurrentUser().getUid());

        Call<Customer> response = iApiCalls.addCustomer(customer);
        response.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                SharedPreferences sharedPreferences = getSharedPreferences("shlishli", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putInt("userId",customer.getCustomerId());
                editor.putString("firebaseUserId", customer.getFirebaseCustomerId());

                editor.apply();
                editor.commit();


                Toast.makeText(EnterDetailsActivity.this, "Welcome to ShliShli "+ customer.getFirstName() , Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(EnterDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {

                Toast.makeText(EnterDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}