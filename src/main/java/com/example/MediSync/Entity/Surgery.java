package com.example.MediSync.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long Id;

    private Date date;

    private  String Name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id")
    @JsonIgnore
    private Hospital hospital;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id")
    @JsonIgnore
    private Doctor doctor;
}
