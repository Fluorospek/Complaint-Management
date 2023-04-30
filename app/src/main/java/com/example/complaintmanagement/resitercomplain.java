package com.example.complaintmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class resitercomplain extends AppCompatActivity {
    TextInputEditText type_field,subject_field,desc_field;
    Button submit_btn;
    private static final String TAG = "resitercomplain";
    private static final String KEY_TYPE="title";
    private static final String KEY_SUB="subject";
    private static final String KEY_DESC="description";
    private static final String KEY_EMAIL="email";
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    FirebaseAuth auth;
    FirebaseUser user;
    ImageView back_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resitercomplain);
        type_field=findViewById(R.id.type);
        subject_field=findViewById(R.id.subject);
        desc_field=findViewById(R.id.complaint_description);
        submit_btn=findViewById(R.id.submit_complaint);
        auth=FirebaseAuth.getInstance();
        back_btn=findViewById(R.id.left_logo);
        user=auth.getCurrentUser();
        if(user==null)
        {
            Intent intent=new Intent(getApplicationContext(),login.class);
            startActivity(intent);
            finish();
        }

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type=type_field.getText().toString();
                String subject=subject_field.getText().toString();
                String desc=desc_field.getText().toString();
                String email=user.getEmail();

                Map<String,Object> Complaint=new HashMap<>();

                Complaint.put(KEY_TYPE,type);
                Complaint.put(KEY_SUB,subject);
                Complaint.put(KEY_DESC,desc);
                Complaint.put(KEY_EMAIL,email);
                if(user!=null){
                    db.collection("complaints").document(user.getUid()).collection("my-complaints").document().set(Complaint)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                Toast.makeText(resitercomplain.this, "Complaint Registered", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {
                                    Toast.makeText(resitercomplain.this, "Error, please try again", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, e.toString());
                                }
                            });
                    }
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

//    public void saveComplaint(View v)
//    {
//        String type=type_field.getText().toString();
//        String subject=subject_field.getText().toString();
//        String desc=desc_field.getText().toString();
//        String email=user.getEmail();
//
//        Map<String,Object> Complaint=new HashMap<>();
//
//        Complaint.put(KEY_TYPE,type);
//        Complaint.put(KEY_SUB,subject);
//        Complaint.put(KEY_DESC,desc);
//        Complaint.put(KEY_EMAIL,email);
//        if(user!=null){
//            db.collection("complaints").document(email).set(Complaint)
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void unused) {
//                            Toast.makeText(resitercomplain.this, "Complaint Registered", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(Exception e) {
//                            Toast.makeText(resitercomplain.this, "Error, please try again", Toast.LENGTH_SHORT).show();
//                            Log.d(TAG, e.toString());
//                        }
//                    });
//        }
//    }
}