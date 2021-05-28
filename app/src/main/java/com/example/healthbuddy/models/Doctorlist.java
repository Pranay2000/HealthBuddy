package com.example.healthbuddy.models;

public class Doctorlist {

    private Integer image;
    private String Name;
    private String Specialization;
    private String Experience;
    private String Contact;
    private String Education;

    public Doctorlist(Integer image, String name, String specialization, String experience, String Education, String Contact) {
        this.image = image;
        this.Name = name;
        this.Specialization = specialization;
        this.Experience = experience;
        this.Education = Education;
        this.Contact = Contact;
    }

    public int getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        this.Specialization = specialization;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        this.Experience = experience;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }
}
