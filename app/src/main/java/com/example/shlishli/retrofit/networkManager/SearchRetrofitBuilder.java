package com.example.shlishli.retrofit.networkManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchRetrofitBuilder {

    private static Retrofit instance;

    private SearchRetrofitBuilder()
    {

    }

    public static Retrofit getInstance()
    {
        if(instance==null)
        {
            synchronized (RetrofitBuilder.class)
            {
                if(instance==null)
                {
                    instance=new Retrofit.Builder()
                            .baseUrl("http://localhost:8081/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(new OkHttpClient())
                            .build();
                }
            }
        }
        return instance;
    }
}

