package com.example.healthbuddy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthbuddy.Doctor_details;
import com.example.healthbuddy.models.Doctorlist;
import com.example.healthbuddy.Patient_DoctorProfileActivity;
import com.example.healthbuddy.R;


public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    Doctorlist[] DList;
    Context context;

    public DoctorAdapter(Doctorlist[] DList, Doctor_details doctorDetails) {
        this.DList = DList;
        this.context = doctorDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.doctor_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Doctorlist DoctorDataList = DList[position];
        holder.DocImage.setImageResource(DoctorDataList.getImage());
        holder.DocName.setText(DoctorDataList.getName());
        holder.DocSpecial.setText(DoctorDataList.getSpecialization());
        holder.DocExp.setText(DoctorDataList.getExperience());

        holder.itemView.setOnClickListener(v -> {
        //    Toast.makeText(context, DoctorDataList.getContact(),Toast.LENGTH_LONG).show();

            String name = DoctorDataList.getName().toString();
            String specialization = DoctorDataList.getSpecialization().toString();
            String contact = DoctorDataList.getContact().toString();
            String experience = DoctorDataList.getExperience().toString();
            String education = DoctorDataList.getEducation().toString();

            Intent intent = new Intent(context, Patient_DoctorProfileActivity.class);
            intent.putExtra("Name",name);
            intent.putExtra("Specialization",specialization);
            intent.putExtra("Contact",contact);
            intent.putExtra("Experience",experience);
            intent.putExtra("Education",education);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return DList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView DocImage;
        TextView DocName;
        TextView DocSpecial;
        TextView DocExp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            DocImage = itemView.findViewById(R.id.DocImg1);
            DocName = itemView.findViewById(R.id.DocName);
            DocSpecial = itemView.findViewById(R.id.DocSpecial);
            DocExp = itemView.findViewById(R.id.DocExp);

        }
    }
}

