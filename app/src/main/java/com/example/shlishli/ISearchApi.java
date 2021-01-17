package com.example.shlishli;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ISearchApi {

    @GET("findByName/{name}")
    Call<List<Search>> getPosts(@Path("name") String name);
}
