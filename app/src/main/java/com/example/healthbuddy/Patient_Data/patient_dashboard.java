package com.example.healthbuddy.Patient_Data;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.healthbuddy.Chatbot.ChatbotMain;
import com.example.healthbuddy.R;
import com.example.healthbuddy.login;

public class patient_dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard);

        //declare java objects for xml widgets
        CardView DoctorDetails, BookAppointment, PatientRecords, PatientChat;

        //Typecast convert xml widgets into java objects
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
                startActivity(new Intent(patient_dashboard.this, com.example.healthbuddy.Patient_Data.BookAppointment.class));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                startActivity(new Intent(patient_dashboard.this, login.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}