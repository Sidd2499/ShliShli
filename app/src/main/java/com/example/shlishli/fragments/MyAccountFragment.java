package com.example.shlishli.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shlishli.MainActivity;
import com.example.shlishli.R;
import com.example.shlishli.activities.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyAccountFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    TextView userName;
    TextView email;
    TextView shoeSize;


    Button editProfileButton;
    Button myLoginHistory;
    Button nyOrdersButton;
    Button logoutButton;


    public MyAccountFragment() {
        // Required empty public constructor
    }


    public static MyAccountFragment newInstance(String param1, String param2) {
        MyAccountFragment fragment = new MyAccountFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_my_account, container, false);
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("shlishli", Context.MODE_PRIVATE);

        userName = view.findViewById(R.id.productFragmentCustomerName);
        email = view.findViewById(R.id.productFragment_EmailDetails);
        shoeSize = view.findViewById(R.id.profileFragment_shoeSize);

        userName.setText(sharedPreferences.getString("name",""));
        email.setText(sharedPreferences.getString("email", ""));
        shoeSize.setText("Shoe Size : "+sharedPreferences.getString("footSize",""));

        editProfileButton = view.findViewById(R.id.profileFragment_editProfileButton);
        myLoginHistory = view.findViewById(R.id.profileFragment_myLoginHistoryButton);
        nyOrdersButton = view.findViewById(R.id.profileFragment_MyOrdersButton);
        logoutButton = view.findViewById(R.id.profileFragment_logoutButton);


        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Hey User! We are still improving. Feel free to explore other features of App!", Toast.LENGTH_SHORT).show();
            }
        });


        myLoginHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Please check your registered email for Login History!", Toast.LENGTH_SHORT).show();

            }
        });

        nyOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                //remove the shared preferences storage keys
                SharedPreferences sharedPreferences= getContext().getSharedPreferences("shlishli", Context.MODE_PRIVATE);

                sharedPreferences.edit().remove("userId").commit();
                sharedPreferences.edit().remove("firebaseUserId").commit();
                sharedPreferences.edit().remove("name").commit();
                sharedPreferences.edit().remove("email").commit();
                sharedPreferences.edit().remove("footSize").commit();



                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }
}