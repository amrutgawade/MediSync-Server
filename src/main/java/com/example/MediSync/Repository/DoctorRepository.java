package com.example.MediSync.Repository;

import com.example.MediSync.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    public Doctor findByEmail(String email);
}
