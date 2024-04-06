package com.example.MediSync.Controller;

import com.example.MediSync.Entity.Patient;
import com.example.MediSync.Repository.PatientRepository;
import com.example.MediSync.Services.PatientService;
import com.example.MediSync.exceptions.UserException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PatientService patientService;

    @PostMapping("/addPatient")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patient.setRole("Patient");
        Patient savePatient = patientRepository.save(patient);
        return new ResponseEntity<Patient>(savePatient, HttpStatus.CREATED);
    }

    @GetMapping("/getPatient")
    public ResponseEntity<Patient> getData(@RequestBody String email) throws UserException , Exception{
//        String jsonString = email;

        // Parse the JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(email);

        // Extract the email address
        String email1 = jsonNode.get("email").asText();
        System.out.println("Email: " + email1);
//        System.out.println(email);
        Patient patientData = patientService.findByEmail(email1);

        return new ResponseEntity<Patient>(patientData,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAll(String email){
        List<Patient> patientData = patientService.findAll();
        return new ResponseEntity<>(patientData,HttpStatus.OK);
    }

}
