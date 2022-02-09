package com.example.healthbuddy.Doctor_Data;

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
                startActivity(new Intent(Doctor_board.this, login.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}