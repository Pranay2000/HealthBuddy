package com.example.healthbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class patient_dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard);

        CardView DoctorDetails, BookAppointment, PatientRecords, PatientChat;

        DoctorDetails = findViewById(R.id.DoctorDetails);
        BookAppointment = findViewById(R.id.BookAppoint);
        PatientRecords = findViewById(R.id.PatientRecords);
        PatientChat = findViewById(R.id.PatientChat);

        DoctorDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(patient_dashboard.this, Doctor_details.class));
            }
        });

        BookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(patient_dashboard.this, BookAppointment.class));
            }
        });

        PatientRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(patient_dashboard.this, retrivemed.class));
            }
        });

        PatientChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(patient_dashboard.this, ChatbotMain.class));
            }
        });
    }
}