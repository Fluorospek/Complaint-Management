package com.example.complaintmanagement;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button logout_btn;
    Button register_btn;
    Button view_btn;
    FirebaseUser user;
    //ActionBar actionBar = getActionBar();
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        OnBackPressedCallback callback=new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                Intent intent=new Intent(getApplicationContext(),login.class);
//                startActivity(intent);
//                finish();
//            }
//        };

        auth=FirebaseAuth.getInstance();
        logout_btn=findViewById(R.id.btn_logout);
        register_btn=findViewById(R.id.btn_registercomplain);
        view_btn=findViewById(R.id.btn_viewcomplain);
        user=auth.getCurrentUser();
        back_btn=findViewById(R.id.left_logo);
        if(user==null){
            Intent intent=new Intent(getApplicationContext(),login.class);
            startActivity(intent);
            finish();
        }

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),resitercomplain.class);
                startActivity(intent);
                finish();
            }
        });

        view_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),viewcomplain.class);
                startActivity(intent);
                finish();
            }
        });


//        actionBar.setDisplayHomeAsUpEnabled(true);

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getApplicationContext(),login.class);
                startActivity(intent);
                finish();
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getApplicationContext(),login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}