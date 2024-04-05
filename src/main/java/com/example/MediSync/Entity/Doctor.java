package com.example.MediSync.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String mobile_no;
    private String gender;
    private String qualification;

    private String password;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "surgery_id")
    private Surgery surgery;

    private String role;


    // Getters and setters


    public Doctor() {
    }

    public Doctor( String first_Name, String last_name, String email, String mobile_No, String gender, String qualification, String password, Hospital hospital, Surgery surgery, String role) {

        this.first_name = first_Name;
        this.last_name = last_name;
        this.email = email;
        this.mobile_no = mobile_No;
        this.gender = gender;
        this.qualification = qualification;
        this.password = password;
        this.hospital = hospital;
        this.surgery = surgery;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_name = first_Name;
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

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_No(String mobile_No) {
        this.mobile_no = mobile_No;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", first_Name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", mobile_No='" + mobile_no + '\'' +
                ", gender='" + gender + '\'' +
                ", qualification='" + qualification + '\'' +
                ", password='" + password + '\'' +
                ", hospital=" + hospital +
                ", surgery=" + surgery +
                ", role='" + role + '\'' +
                '}';
    }
}