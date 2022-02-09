package com.example.healthbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import com.example.healthbuddy.Doctor_Data.Doctor_board;
import com.example.healthbuddy.Patient_Data.patient_dashboard;

public class frontpage extends AppCompatActivity {
    public Button Signin;
    public Button Signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String value = (sharedPreferences.getString("DoctorPatient", "2"));
        if(value.equals("1")) {
            Intent doctorDashboard = new Intent(frontpage.this, Doctor_board.class);
            startActivity(doctorDashboard);
            finish();
        } else if(value.equals("0")) {
            Intent patientDashboard = new Intent(frontpage.this, patient_dashboard.class);
            startActivity(patientDashboard);
            finish();
        }

        setContentView(R.layout.front_page);
        Signin = (Button) findViewById(R.id.signin);

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent login = new Intent(frontpage.this, login.class);
                    startActivity(login);
                    finish();
                }
            });
        Signup = (Button) findViewById(R.id.signup);
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(frontpage.this,registerpage.class);
                startActivity(signup);
            }
        });
    }
}