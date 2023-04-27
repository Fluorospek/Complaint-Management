package com.example.complaintmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class resitercomplain extends AppCompatActivity {
    TextInputEditText type_field,subject_field,desc_field;
    Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resitercomplain);
        type_field=findViewById(R.id.type);
        subject_field=findViewById(R.id.subject);
        desc_field=findViewById(R.id.complaint_description);
        submit_btn=findViewById(R.id.submit_complaint);
    }
}