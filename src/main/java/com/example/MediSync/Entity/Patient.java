package com.example.MediSync.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patient_Name;
    private String email;
    private Integer mobile_No;
    private String gender;
    private String address;
    private List<String> Allergy = new ArrayList<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MedicalTest> tests = new ArrayList<>();

//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
@JsonIgnore
private List<Surgery> surgeries = new ArrayList<>(); // List of surgeries

    private List<String> Current_Medicine = new ArrayList<>();

    public Patient(String patient_Name, String email, Integer mobile_No, String gender, String address, List<String> allergy, List<MedicalTest> tests, List<Surgery> surgeries, List<String> current_Medicine) {
        this.patient_Name = patient_Name;
        this.email = email;
        this.mobile_No = mobile_No;
        this.gender = gender;
        this.address = address;
        Allergy = allergy;
        this.tests = tests;
        this.surgeries = surgeries;
        Current_Medicine = current_Medicine;
    }

    // Getters and

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatient_Name() {
        return patient_Name;
    }

    public void setPatient_Name(String patient_Name) {
        this.patient_Name = patient_Name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getAllergy() {
        return Allergy;
    }

    public void setAllergy(List<String> allergy) {
        Allergy = allergy;
    }

    public List<MedicalTest> getTests() {
        return tests;
    }

    public void setTests(List<MedicalTest> tests) {
        this.tests = tests;
    }

    public List<Surgery> getSurgeries() {
        return surgeries;
    }

    public void setSurgeries(List<Surgery> surgeries) {
        this.surgeries = surgeries;
    }

    public List<String> getCurrent_Medicine() {
        return Current_Medicine;
    }

    public void setCurrent_Medicine(List<String> current_Medicine) {
        Current_Medicine = current_Medicine;
    }
}
