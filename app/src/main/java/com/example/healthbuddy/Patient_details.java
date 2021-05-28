package com.example.healthbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthbuddy.models.BookedAppointmentList;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Patient_details extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private RecyclerView recyclerView;
    private String currentUID, date, time, name, pid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        currentUID = mAuth.getCurrentUser().getUid().toString();

        recyclerView = (RecyclerView) findViewById(R.id.detailsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        Query query = mDatabase.child("Appointments");
        FirebaseRecyclerOptions<BookedAppointmentList> firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<BookedAppointmentList>()
                .setQuery(query, BookedAppointmentList.class)
                .build();

        FirebaseRecyclerAdapter<BookedAppointmentList, DoctorShowAppointmentVH> firebaseRecyclerAdapter1 =
                new FirebaseRecyclerAdapter<BookedAppointmentList, DoctorShowAppointmentVH>(firebaseRecyclerOptions) {

                    @Override
                    public DoctorShowAppointmentVH onCreateViewHolder(ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.show_patient_details, parent, false);
                        return new DoctorShowAppointmentVH(view);
                    }

                    @Override
                    protected void onBindViewHolder(@NonNull final DoctorShowAppointmentVH holder, final int position, @NonNull final BookedAppointmentList model) {

                        holder.mView.setOnClickListener(v -> {

                            pid = model.getPatientID();
                            date = model.getDate();
                            time = model.getTime();
                            alertDialog();
                        });

                        mDatabase.child("Appointments").child("Appointment Timings").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String date = snapshot.child("Appointment Date").getValue(String.class);
                                holder.setDate(date);

                                String time = snapshot.child("Appointment Time").getValue(String.class);
                                holder.setTime(time);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(Patient_details.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
                            }
                        });

                        mDatabase.child("Users").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String name = dataSnapshot.child("name").getValue(String.class);
                                holder.setName(name);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Toast.makeText(Patient_details.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                };

        recyclerView.setAdapter(firebaseRecyclerAdapter1);
        firebaseRecyclerAdapter1.startListening();
    }

    private void alertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Patient_details.this);
        builder.setIcon(R.drawable.question).setTitle("Cancel Appointment");
        builder.setMessage("Are You Sure! Want to Cancel Appointment");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mDatabase.child("Appointments").removeValue();
                onStart();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public class DoctorShowAppointmentVH extends RecyclerView.ViewHolder{

        View mView;

        public DoctorShowAppointmentVH(View itemView) {
            super(itemView);

            mView =itemView;
        }

        public void setDate(String date) {

            TextView textView = (TextView) mView.findViewById(R.id.date);
            textView.setText(date);
        }

        public void setTime(String time) {
            TextView textView = (TextView) mView.findViewById(R.id.time);
            textView.setText(time);
        }

        public void setName(String name) {
            TextView mName = (TextView) mView.findViewById(R.id.patientName);
            mName.setText(name);
        }
    }
}