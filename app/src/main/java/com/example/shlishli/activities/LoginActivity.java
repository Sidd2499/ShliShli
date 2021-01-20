package com.example.shlishli.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shlishli.MainActivity;
import com.example.shlishli.R;
import com.example.shlishli.apiCalls.IApiCalls;
import com.example.shlishli.dataModels.Customer;
import com.example.shlishli.dataModels.UserActivity;
import com.example.shlishli.retrofit.networkManager.RetrofitBuilder;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 10;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private Button googlesSignInBtn;
    private ProgressBar progressBar;
    private EditText etLoginEmail;
    private EditText etLoginPassword;
    private TextView tvRegisterhere;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
        //Log.d("LoginID",mAuth.getCurrentUser().getUid());
        progressBar=(ProgressBar)findViewById(R.id.login_progressBar);
        googlesSignInBtn=(Button)findViewById(R.id.btn_google_login);
        etLoginEmail=(EditText)findViewById(R.id.et_login_email);
        etLoginPassword=(EditText)findViewById(R.id.et_login_password);
        btnLogin=(Button)findViewById(R.id.btn_login_enter_details);
        tvRegisterhere=(TextView) findViewById(R.id.tv_register_here);
       // getSupportActionBar().hide();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso);

        googlesSignInBtn.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            signIn();
        });

        btnLogin.setOnClickListener(v -> {
            signInWithEmailAndPassword();
            progressBar.setVisibility(View.VISIBLE);
        });
        tvRegisterhere.setOnClickListener(v -> {
            Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

    }

    private void signInWithEmailAndPassword() {
        String email=etLoginEmail.getText().toString();
        String password=etLoginPassword.getText().toString();
        if(TextUtils.isEmpty(email))
        {
            etLoginEmail.setError("Enter Email");
            return;
        }
        else if(TextUtils.isEmpty(password))
        {
            etLoginPassword.setError("Password cannot be empty");
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this, "Sign In Successful", Toast.LENGTH_SHORT).show();


                    checkIfUserDetailsAreAvailable();



                }
                else
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this, "Error while Sign In"+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {

                Toast.makeText(this,"Failed to authenticate "+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.INVISIBLE);
                            checkIfUserDetailsAreAvailable();
                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(LoginActivity.this, "Sorry Authentication Failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    private void checkIfUserDetailsAreAvailable(){
        Retrofit retrofit = RetrofitBuilder.getInstance();
        IApiCalls iApiCalls = retrofit.create(IApiCalls.class);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        boolean userDetailsAvailable = false;

        Call<Customer> response = iApiCalls.getUserDetailsByFirebaseUID(user.getUid());

        response.enqueue(new Callback<Customer>() {

            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {

                Customer customer = response.body();


                if(customer.getFirstName() != null) {
                    SharedPreferences sharedPreferences = getSharedPreferences("shlishli", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putInt("userId", customer.getCustomerId());
                    editor.putString("firebaseUserId", response.body().getFirebaseCustomerId());
                    editor.putString("name",response.body().getFirstName());
                    editor.putString("email",FirebaseAuth.getInstance().getCurrentUser().getEmail());
                    editor.putString("footSize",response.body().getFootSize());

                    editor.apply();
                    editor.commit();

                    UserActivity userActivity = new UserActivity();

                    userActivity.setCustomerId(new Long(customer.getCustomerId()));

                    Call<Void> logUserActivity = iApiCalls.logUserActivity(userActivity);

                    logUserActivity.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(LoginActivity.this, "Welcome to ShliShli " + customer.getFirstName(), Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "This session is not recorded" + customer.getFirstName(), Toast.LENGTH_SHORT).show();


                            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });



                }

                else {
                    Toast.makeText(LoginActivity.this, "User Details Not Found, Please Register", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(LoginActivity.this, EnterDetailsActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "User Details Not Found. Please Register on ShliShli", Toast.LENGTH_SHORT).show();

                // Redirect the user to register on ShliShli

                Intent intent=new Intent(LoginActivity.this, EnterDetailsActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}