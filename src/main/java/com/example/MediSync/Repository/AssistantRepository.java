package com.example.MediSync.Repository;

import com.example.MediSync.Entity.Assistant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssistantRepository extends JpaRepository<Assistant,Long> {
    public Assistant findByEmail(String email);

}
