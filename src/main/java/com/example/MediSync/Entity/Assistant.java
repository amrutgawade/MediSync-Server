package com.example.MediSync.Entity;

import jakarta.persistence.*;

public class Assistant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_Name;

    private String last_name;
    private String email;
    private Integer mobile_No;
    private String gender;

    private String password;


    public Assistant(String first_Name, String last_name, String email, Integer mobile_No, String gender, String password) {
        this.first_Name = first_Name;
        this.last_name = last_name;
        this.email = email;
        this.mobile_No = mobile_No;
        this.gender = gender;
        this.password = password;
    }
}
