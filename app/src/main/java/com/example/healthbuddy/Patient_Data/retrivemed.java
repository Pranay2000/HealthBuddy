package com.example.healthbuddy.Patient_Data;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthbuddy.Patient_Data.RetrievePDF;
import com.example.healthbuddy.R;

public class retrivemed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrivemed);
    }

    public void retrievePDF1(View view) {
        startActivity(new Intent(getApplicationContext(), RetrievePDF.class));
    }
}