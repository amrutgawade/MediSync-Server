package com.example.MediSync.Controller;

import com.example.MediSync.Entity.Patient;
import com.example.MediSync.Repository.PatientRepository;
import com.example.MediSync.Request.LoginRequest;
import com.example.MediSync.Responces.AuthResponse;
import com.example.MediSync.Services.AssistantService;
import com.example.MediSync.Services.CustomeAssistantServiceImplementation;
import com.example.MediSync.Services.CustomePatientServiceImplementation;
import com.example.MediSync.Services.PatientService;
import com.example.MediSync.config.JWTProvider;
import com.example.MediSync.exceptions.UserException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private CustomePatientServiceImplementation customePatientService;

    @PostMapping("/addPatient")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patient.setRole("Patient");
        Patient savePatient = patientRepository.save(patient);
        return new ResponseEntity<Patient>(savePatient, HttpStatus.CREATED);
    }




    @PostMapping("/getPatient")
    public ResponseEntity<Patient> getData(@RequestBody String email) throws UserException , Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(email);

        // Extract the email address
        String email1 = jsonNode.get("email").asText();
        System.out.println("Email: " + email1);
        Patient patientData = patientService.findByEmail(email1);

        return new ResponseEntity<Patient>(patientData,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAll(String email){
        List<Patient> patientData = patientService.findAll();
        return new ResponseEntity<>(patientData,HttpStatus.OK);
    }

}
