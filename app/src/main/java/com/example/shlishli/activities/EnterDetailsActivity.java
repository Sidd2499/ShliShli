package com.example.shlishli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shlishli.MainActivity;
import com.example.shlishli.R;

public class EnterDetailsActivity extends AppCompatActivity {

    private EditText tvFirstName;
    private EditText tvLastName;
    private EditText tvMobileNumber;
    private EditText tvAge;
    private EditText tvFootSize;
    private EditText tvAddress;
    private Button btnAddDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);
        tvFirstName=(EditText)findViewById(R.id.tv_firstname);
        tvLastName=(EditText)findViewById(R.id.tv_lastname);
        tvMobileNumber=(EditText)findViewById(R.id.tv_age);
        tvFootSize=(EditText)findViewById(R.id.tv_address);
        btnAddDetails=(Button)findViewById(R.id.btn_enter_details);
        btnAddDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDetails();
                Intent intent=new Intent(EnterDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void addDetails() {

    }
}