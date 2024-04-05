package com.example.MediSync.Controller;

import com.example.MediSync.Entity.Patient;
import com.example.MediSync.Repository.PatientRepository;
import com.example.MediSync.Services.PatientService;
import com.example.MediSync.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientService patientService;

    @PostMapping("/addPatient")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        Patient savePatient = patientRepository.save(patient);
        return new ResponseEntity<Patient>(savePatient, HttpStatus.CREATED);
    }

    @GetMapping("/getPatient")
    public ResponseEntity<Patient> getData(@RequestBody String email) throws UserException {
        Patient patientData = patientService.findByEmail(email);
        return new ResponseEntity<Patient>(patientData,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAll(String email){
        List<Patient> patientData = patientService.findAll();
        return new ResponseEntity<>(patientData,HttpStatus.OK);
    }


}
