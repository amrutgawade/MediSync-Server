package com.example.MediSync.Entity;

import jakarta.persistence.*;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long Id;
    private String Doctor_Name;

    private String Email;
    private Integer Mobile_No;

    private String Gender;
    private String Qualification;

    @OneToOne(mappedBy = "hospital", cascade = CascadeType.ALL)
    private String Hospital_Name;

    @OneToOne(mappedBy = "surgery", cascade = CascadeType.ALL)
    private Surgery surgery;



}
