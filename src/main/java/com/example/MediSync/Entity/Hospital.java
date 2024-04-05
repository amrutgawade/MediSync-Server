package com.example.MediSync.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long Id;
    private String Hospital_Name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id")
    @JsonIgnore
    private List<Doctor> doctors = new ArrayList<>();

    @OneToOne(mappedBy = "surgery", cascade = CascadeType.ALL)
    private Surgery surgery;


}
