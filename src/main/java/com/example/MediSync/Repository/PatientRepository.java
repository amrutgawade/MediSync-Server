package com.example.MediSync.Repository;

import com.example.MediSync.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    public Patient findByEmail(String email);
}
