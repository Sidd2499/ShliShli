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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shlishli.R;
import com.example.shlishli.activities.LoginActivity;
import com.example.shlishli.adapters.MyCartAdapter;
import com.example.shlishli.adapters.TopProductsAdapter;
import com.example.shlishli.apiCalls.IApiCalls;
import com.example.shlishli.dataModels.CartItem;
import com.example.shlishli.dataModels.MyCartItem;
import com.example.shlishli.dataModels.Product;
import com.example.shlishli.dataModels.ResponseTemplateCartItems;
import com.example.shlishli.retrofit.networkManager.RetrofitBuilder;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyCartFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public MyCartFragment() {
        // Required empty public constructor
    }

    public static MyCartFragment newInstance(String param1, String param2) {
        MyCartFragment fragment = new MyCartFragment();
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

    private SharedPreferences sharedPreferences;
    private RecyclerView myCartRecyclerView;
    private List<MyCartItem> cartItemList=new ArrayList<>();
    private TextView tvTotalAmount;

    private Button buyNowButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        sharedPreferences=this.getActivity().getSharedPreferences("shlishli", Context.MODE_PRIVATE);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        View view=inflater.inflate(R.layout.fragment_my_cart, container, false);
        tvTotalAmount=view.findViewById(R.id.tv_total_amount);


        buyNowButton = view.findViewById(R.id.myCartFragment_buyNowButton);



        buyNowButton.setOnClickListener(v -> {
            // make an API call to checkout items




        });

        // check if the user is logged in or not

        if(firebaseAuth.getCurrentUser() == null){
            // show a dialog and make the intent to login screen
            Toast.makeText(view.getContext(), "Please Sign In to Continue", Toast.LENGTH_SHORT).show();

            Intent goToSignInPage = new Intent(view.getContext(), LoginActivity.class);
            startActivity(goToSignInPage);

            // TODO: place a finish function here so user can't return back


        }
        else{
            getCartList(view);
            myCartRecyclerView=view.findViewById(R.id.mycart_recycler_view);
        }



        return view;
    }

    private void getCartList(View view) {

        Retrofit retrofitBuilder = RetrofitBuilder.getInstance();
        IApiCalls iApiCalls = retrofitBuilder.create(IApiCalls.class);

        Long userid=Long.valueOf(sharedPreferences.getInt("userId",0));
        Call<ResponseTemplateCartItems> responses = iApiCalls.getCartDetails(userid);

        responses.enqueue(new Callback<ResponseTemplateCartItems>() {
            @Override
            public void onResponse(Call<ResponseTemplateCartItems> call, Response<ResponseTemplateCartItems> response) {

                if(null!=response.body()) {
                    cartItemList = response.body().getData();
                    double sum=0.0;
                    for(int i=0;i<cartItemList.size();i++)
                    {
                        sum+=cartItemList.get(i).getPrice();
                    }
                    tvTotalAmount.append(String.valueOf(sum));
                    MyCartAdapter myCartAdapter=new MyCartAdapter(cartItemList);
                    myCartRecyclerView.setHasFixedSize(true);
                    myCartRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                    myCartRecyclerView.setAdapter(myCartAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseTemplateCartItems> call, Throwable t) {

            }
        });
    }

    }