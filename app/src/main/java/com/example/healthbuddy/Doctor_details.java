package com.example.healthbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.Adapter.DoctorAdapter;
import com.example.healthbuddy.models.Doctorlist;

public class Doctor_details extends AppCompatActivity {

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_displayview);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Doctorlist[] DList = new Doctorlist[]{
                new Doctorlist(R.drawable.doctor, "Dr. Mona Kataria", "Homeopathy", "5 years overall Experience", "MBBS", "9865321470" ),
                new Doctorlist(R.drawable.doctor, "Dr. Ranjana Gupta", "Homeopathy", "7 years overall Experience", "MBBS", "9876543210"),
                new Doctorlist(R.drawable.doctor, "Dr. Aditya Kapoor", "Homeopathy", "10 years overall Experience", "MBBS", "7894561230"),
                new Doctorlist(R.drawable.doctor, "Dr. Anupam Kumar", "Homeopathy", "15 years overall Experience", "MBBS MD", "8877556310"),
                new Doctorlist(R.drawable.doctor, "Dr. Ashok Thapa", "Homeopathy", "8 years overall Experience", "MBBS", "9911746850"),
        };

        DoctorAdapter DoctorAdapterList = new DoctorAdapter(DList, Doctor_details.this);
        recyclerView.setAdapter(DoctorAdapterList);

    }
}
