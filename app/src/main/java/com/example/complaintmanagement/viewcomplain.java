package com.example.complaintmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class viewcomplain extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    private static final String TAG = "resitercomplain";
    private static final String KEY_TYPE="title";
    private static final String KEY_SUB="subject";
    private static final String KEY_DESC="description";
    private static final String KEY_EMAIL="email";
    private FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcomplain);
        auth=FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
        String email = user.getEmail();
        if(user==null)
        {
            Intent intent=new Intent(getApplicationContext(),login.class);
            startActivity(intent);
            finish();
        }

        db.document("complaints").collection(email).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot>  documents=queryDocumentSnapshots.getDocuments();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(viewcomplain.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}