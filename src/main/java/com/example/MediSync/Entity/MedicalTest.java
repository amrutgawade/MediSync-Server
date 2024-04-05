package com.example.MediSync.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class MedicalTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long Id;

    private String Test_Name;

    private Date date;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @JsonIgnore
    private Patient patient;
}
