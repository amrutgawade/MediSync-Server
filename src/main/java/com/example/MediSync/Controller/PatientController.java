package com.example.MediSync.Controller;

import com.example.MediSync.Entity.Patient;
import com.example.MediSync.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        Patient savePatient = patientRepository.save(patient);
        return new ResponseEntity<Patient>(savePatient, HttpStatus.ACCEPTED);
    }

}
