package com.example.MediSync.Services;

import com.example.MediSync.Entity.Patient;
import com.example.MediSync.Repository.PatientRepository;
import com.example.MediSync.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImplementation implements PatientService{

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    public PatientServiceImplementation(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient findByEmail(String email) throws UserException{
        Patient getPatient = patientRepository.findByEmail(email);
        if (getPatient == null){
            throw new UserException("Patient Not Found");
        }
        return getPatient;

    }

    @Override
    public List<Patient> findAll(){
        return patientRepository.findAll();
    }
}
