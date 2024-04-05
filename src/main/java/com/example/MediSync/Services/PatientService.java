package com.example.MediSync.Services;

import com.example.MediSync.Entity.Patient;
import com.example.MediSync.exceptions.UserException;

import java.util.List;

public interface PatientService {
    public Patient findByEmail(String email) throws UserException;

    public List<Patient> findAll();
}
