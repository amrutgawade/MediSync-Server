package com.example.MediSync.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_Name;

    private String last_name;
    private String email;
    private Integer mobile_No;
    private String gender;
    private String qualification;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "surgery_id")
    private Surgery surgery;


    // Getters and setters


    public Doctor(String first_name, String last_name,String email, Integer mobile_No, String gender, String qualification, Hospital hospital, Surgery surgery) {
        this.first_Name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.mobile_No = mobile_No;
        this.gender = gender;
        this.qualification = qualification;
        this.hospital = hospital;
        this.surgery = surgery;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMobile_No() {
        return mobile_No;
    }

    public void setMobile_No(Integer mobile_No) {
        this.mobile_No = mobile_No;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Surgery getSurgery() {
        return surgery;
    }

    public void setSurgery(Surgery surgery) {
        this.surgery = surgery;
    }

}
