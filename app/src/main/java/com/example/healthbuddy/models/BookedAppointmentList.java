package com.example.healthbuddy.models;

public class BookedAppointmentList {

    private String Date;
    private String Time;
    private String PatientID;

    public BookedAppointmentList() {
    }

    public BookedAppointmentList(String date, String time,  String patientID) {
        this.Date = date;
        this.Time = time;
        this.PatientID = patientID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        this.Time = time;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String patientID) {
        this.PatientID = patientID;
    }
}

