package com.example.shlishli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.shlishli.R;

public class ItemDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_display);
        getSupportActionBar().hide();
    }
}