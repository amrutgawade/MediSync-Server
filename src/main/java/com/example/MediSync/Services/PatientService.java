package com.example.MediSync.Services;

import com.example.MediSync.Entity.Assistant;
import com.example.MediSync.Entity.Patient;
import com.example.MediSync.exceptions.UserException;

import java.util.List;

public interface PatientService {
    public Patient findByEmail(String email) throws UserException;

    public List<Patient> findAll();

    public Patient findById(Long assistantId) throws UserException;

    public Patient findUserProfileByJwt(String jwt) throws UserException;
}
