package com.example.shlishli;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.shlishli.activities.LoginActivity;
import com.example.shlishli.activities.SearchActivity;
import com.example.shlishli.fragments.HomeFragment;
import com.example.shlishli.fragments.MyAccountFragment;
import com.example.shlishli.fragments.MyCartFragment;
import com.example.shlishli.fragments.MyOrdersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation_tab);
        FrameLayout frameLayout=(FrameLayout)findViewById(R.id.frame_layout);
        toolbar=findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,new HomeFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.it_navi_my_cart:
                        setFragment(new MyCartFragment());
                        return true;
                    case R.id.it_navi_home:
                        setFragment(new HomeFragment());
                        return true;
                    case R.id.it_navi_my_orders:
                        setFragment(new MyOrdersFragment());
                        return true;
                    case R.id.it_navi_my_account:
                        setFragment(new MyAccountFragment());
                        return true;
                    default:
                        return false;

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        MenuItem searchItem=menu.findItem(R.id.btn_search);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.it_logout)
        {
            Log.d("LogOut","Logout is selected");

            FirebaseAuth.getInstance().signOut();

            //remove the shared preferences storage keys
            SharedPreferences sharedPreferences= getSharedPreferences("shlishli", Context.MODE_PRIVATE);

            sharedPreferences.edit().remove("userId").commit();
            sharedPreferences.edit().remove("firebaseUserId").commit();
            sharedPreferences.edit().remove("name").commit();
            sharedPreferences.edit().remove("email").commit();
            sharedPreferences.edit().remove("footSize").commit();



            Intent intent=new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);

        }
        if(item.getItemId()==R.id.btn_search)
        {
            Log.d("Search","Search is selected");
            Intent intent=new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setFragment(Fragment myFragment) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,myFragment).commit();
    }
}