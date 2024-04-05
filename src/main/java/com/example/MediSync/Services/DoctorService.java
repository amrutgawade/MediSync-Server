package com.example.MediSync.Services;

import com.example.MediSync.Entity.Doctor;
import com.example.MediSync.exceptions.UserException;

public interface DoctorService {
    public Doctor findById(Long doctorId) throws UserException;

    public Doctor findUserProfileByJwt(String jwt) throws UserException;

    public Doctor updateUser(String jwt,Doctor doctor) throws UserException;
}
