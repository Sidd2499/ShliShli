package com.example.shlishli;

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
            synchronized (SearchRetrofitBuilder.class)
            {
                if(instance==null)
                {
                    instance=new Retrofit.Builder()
                            .baseUrl("http://172.16.20.12:8081/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(new OkHttpClient())
                            .build();
                }
            }
        }
        return instance;
    }
}
