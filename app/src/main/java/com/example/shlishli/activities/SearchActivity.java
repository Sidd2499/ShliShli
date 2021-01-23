package com.example.shlishli.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shlishli.R;
import com.example.shlishli.adapters.SearchRecyclerAdapter;
import com.example.shlishli.apiCalls.IApiCalls;
import com.example.shlishli.dataModels.Search;
import com.example.shlishli.retrofit.networkManager.RetrofitBuilder;
import com.example.shlishli.retrofit.networkManager.SearchRetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchActivity extends AppCompatActivity {

    private EditText etSeearchQuery;
    private Button btnSearch;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        etSeearchQuery=(EditText)findViewById(R.id.et_search_query);
        btnSearch=(Button)findViewById(R.id.btn_search_action);
        recyclerView=(RecyclerView)findViewById(R.id.search_recycler_view);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchInit();
            }
        });
    }

    private void searchInit() {
        String searchQuery=etSeearchQuery.getText().toString();
        Retrofit retrofit= SearchRetrofitBuilder.getInstance();
        IApiCalls iApiCalls =retrofit.create(IApiCalls.class);
        Call<List<Search>> responses= iApiCalls.getPosts(searchQuery);

        responses.enqueue(new Callback<List<Search>>() {
            @Override
            public void onResponse(Call<List<Search>> call, Response<List<Search>> response) {
                if(null!=response.body()) {
                    List<Search> searches = new ArrayList<>();
                    for (Search arr : response.body()) {
                        searches.add(arr);
                    }

                     SearchRecyclerAdapter searchRecyclerAdapter=new SearchRecyclerAdapter(searches);
                     recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                     recyclerView.setAdapter(searchRecyclerAdapter);
                     searchRecyclerAdapter.notifyDataSetChanged();
                     searchRecyclerAdapter.notify();
                }
            }

            @Override
            public void onFailure(Call<List<Search>> call, Throwable t) {
                Log.d("RecyclerView","Cannot hit api"+t.getMessage());
            }
        });
    }
}