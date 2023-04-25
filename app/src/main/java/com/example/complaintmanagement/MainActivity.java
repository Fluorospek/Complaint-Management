package com.example.complaintmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button logout_btn;
    Button register_btn;
    Button view_btn;
    TextView textView;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth=FirebaseAuth.getInstance();
        logout_btn=findViewById(R.id.btn_logout);
        register_btn=findViewById(R.id.btn_registercomplain);
        view_btn=findViewById(R.id.btn_viewcomplain);
//        textView=findViewById(R.id.user_details);
        user=auth.getCurrentUser();
        if(user==null){
            Intent intent=new Intent(getApplicationContext(),login.class);
            startActivity(intent);
            finish();
        }
        else{
            textView.setText(user.getEmail());
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

        logout_btn.setOnClickListener(new View.OnClickListener() {
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