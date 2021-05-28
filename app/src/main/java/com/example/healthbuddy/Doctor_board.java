package com.example.healthbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Doctor_board extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_board);

        CardView PatientDetails, MedRecords, ChatBox;

        PatientDetails = findViewById(R.id.PatientDetails);
        MedRecords = findViewById(R.id.MedRecords);
        ChatBox = findViewById(R.id.ChatBox);

        PatientDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Doctor_board.this, Patient_details.class));
            }
        });

        MedRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Doctor_board.this, medRecords.class));
            }
        });

        ChatBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Doctor_board.this, ChatbotMain.class));
            }
        });

    }
}