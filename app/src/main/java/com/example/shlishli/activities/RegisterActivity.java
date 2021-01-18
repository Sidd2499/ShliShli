package com.example.shlishli.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shlishli.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private EditText etCheckPassword;
    private Button btnRegister;
    private TextView tvClickToSignIn;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etEmail=(EditText)findViewById(R.id.et_login_email);
        etPassword=(EditText)findViewById(R.id.et_password);
        etCheckPassword=(EditText)findViewById(R.id.et_check_password);
        tvClickToSignIn=(TextView)findViewById(R.id.tv_click_here_register);
        btnRegister=(Button)findViewById(R.id.btn_register);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        tvClickToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this, EnterDetailsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void register() {
        String email=etEmail.getText().toString();
        String password=etPassword.getText().toString();
        String checkPassword=etCheckPassword.getText().toString();
        if(TextUtils.isEmpty(email))
        {
            etEmail.setError("Enter Email");
            return;
        }
        else if(TextUtils.isEmpty(checkPassword))
        {
            etCheckPassword.setError("Password cannot be empty");
            return;
        }
        else if(TextUtils.isEmpty(password))
        {
            etPassword.setError("Password cannot be empty");
            return;
        }
        else if(!password.equals(checkPassword))
        {
            etPassword.setError("Passwords dont match");
            return;
        }
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(RegisterActivity.this,EnterDetailsActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Sign Up Failed!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }
}