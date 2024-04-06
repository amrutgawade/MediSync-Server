package com.example.MediSync.Services;

import com.example.MediSync.Entity.Assistant;
import com.example.MediSync.Entity.Patient;
import com.example.MediSync.Repository.AssistantRepository;
import com.example.MediSync.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomePatientServiceImplementation implements UserDetailsService {

    private PatientRepository patientRepository;

    public CustomePatientServiceImplementation(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Autowired


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient patient = patientRepository.findByEmail(username);

        if (patient == null) {
            throw new UsernameNotFoundException("User Not Found With Email"+username);
        }

        List<GrantedAuthority> authorities;
        authorities = Arrays.stream(patient.getRole().split(","))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(patient.getEmail(),patient.getPassword(),authorities);
    }
}
