package com.example.shlishli.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shlishli.R;
import com.example.shlishli.activities.LoginActivity;
import com.example.shlishli.adapters.MyCartAdapter;
import com.example.shlishli.adapters.MyOrderAdapter;
import com.example.shlishli.apiCalls.IApiCalls;
import com.example.shlishli.dataModels.OrderItem;
import com.example.shlishli.retrofit.networkManager.RetrofitBuilder;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyOrdersFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private List<OrderItem> orderItems;


    private RecyclerView myOrdersRecyclerView;

    public MyOrdersFragment() {
        // Required empty public constructor
    }

    public static MyOrdersFragment newInstance(String param1, String param2) {
        MyOrdersFragment fragment = new MyOrdersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View view=inflater.inflate(R.layout.fragment_my_orders, container, false);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            // show a dialog and make the intent to login screen
            Toast.makeText(view.getContext(), "Please Sign In to Continue", Toast.LENGTH_SHORT).show();

            Intent goToSignInPage = new Intent(view.getContext(), LoginActivity.class);
            startActivity(goToSignInPage);



        }
        else{
            getOrderItems(view);

            myOrdersRecyclerView = view.findViewById(R.id.myOrdersRecyclerView);
        }





        return view;
    }


    private void getOrderItems(View view){
        Retrofit retrofitBuilder = RetrofitBuilder.getInstance();
        IApiCalls iApiCalls = retrofitBuilder.create(IApiCalls.class);


        SharedPreferences sharedPreferences= getContext().getSharedPreferences("shlishli", Context.MODE_PRIVATE);





        Call<List<OrderItem>> responses = iApiCalls.getCustomerOrders(new Long(sharedPreferences.getInt("userId", 0)));


        responses.enqueue(new Callback<List<OrderItem>>() {
            @Override
            public void onResponse(Call<List<OrderItem>> call, Response<List<OrderItem>> response) {
               orderItems = response.body();

                if(orderItems.size() > 0){


                    MyOrderAdapter myOrderAdapter=new MyOrderAdapter(orderItems);
                    myOrdersRecyclerView.setHasFixedSize(true);
                    myOrdersRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                    myOrdersRecyclerView.setAdapter(myOrderAdapter);
                }
                else{
                    Toast.makeText(getContext(), "You have no orders! Feel free to explore our Market Place!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<OrderItem>> call, Throwable t) {

            }
        });
    }
}