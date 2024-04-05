package com.example.MediSync.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long Id;
    private String Patient_Name;
    private String Email;
    private Integer Mobile_No;
    private  String Gender;

    private String Address;

    private List<String> Allergy = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Surgery> surgery = new ArrayList<>();

    private List<String> Current_Medicine = new ArrayList<>();

    @OneToMany(mappedBy = "medical_test",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MedicalTest> tests = new ArrayList<>();
}
