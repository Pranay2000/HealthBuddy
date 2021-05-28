package com.example.healthbuddy;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class Patient_DoctorProfileActivity extends AppCompatActivity {

    private TextView Name, Specialization, Experience, ContactNo, Education;
    TextView textView;
    DatabaseReference mreference;

    Button BookAppointBtn;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    int t1hour, t1minute;

    private String TAG = "Patient_DoctorProfileActivity";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__doctor_profile);

        Log.d(TAG, "ON Created Started");
        Name = (TextView) findViewById(R.id.docProfileName);
        Specialization = (TextView) findViewById(R.id.docProfileSpecial);
        Experience = (TextView) findViewById(R.id.docProfileExp);
        Education = (TextView) findViewById(R.id.docProfileEdu);
        ContactNo = (TextView) findViewById(R.id.docProfileContact);

        BookAppointBtn = (Button) findViewById(R.id.book_appoint_button);

        BookAppointBtn.setOnClickListener((v) -> {

            Log.d(TAG, "ON BookAppointBtn Started");

            calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

            datePickerDialog = new DatePickerDialog(Patient_DoctorProfileActivity.this, (view, year1, month1, dayOfMonth) -> {

                String date = dayOfMonth + "-" + (month + 1) + "-" + year;
                // Toast.makeText(Patient_DoctorProfileActivity.this, date , Toast.LENGTH_SHORT).show();

            TimePickerDialog timePickerDialog = new TimePickerDialog(Patient_DoctorProfileActivity.this,
                    (view1, hourOfDay, minute) -> {
                        t1hour = hourOfDay;
                        t1minute = minute;
                        calendar = Calendar.getInstance();
                        calendar.set(0, 0, 0, t1hour, t1minute);
                        String time = t1hour + ":" + t1minute;

                        mreference = FirebaseDatabase.getInstance().getReference("Appointments").child("Appointment Timings");
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("Appointment Time",time);
                        hashMap.put("Appointment Date",date);


                        mreference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent intent = new Intent(Patient_DoctorProfileActivity.this, BookAppointment.class);
                                startActivity(intent);
                            }
                        });
                    }, 12, 0, false);
                timePickerDialog.updateTime(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
                timePickerDialog.show();
            }, day, month, year);
            datePickerDialog.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + (3 * 60 * 60 * 1000));
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + (15 * 24 * 60 * 60 * 1000));
            datePickerDialog.show();
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "ON start Started");
        String name = getIntent().getStringExtra("Name").toString();
        String education = getIntent().getStringExtra("Education").toString();
        String specialization = getIntent().getStringExtra("Specialization").toString();
        String experience = getIntent().getStringExtra("Experience").toString();
        String contact = getIntent().getStringExtra("Contact").toString();

        Name.setText(name);
        Specialization.setText(specialization);
        Experience.setText(experience);
        Education.setText(education);
        ContactNo.setText(contact);
    }
}