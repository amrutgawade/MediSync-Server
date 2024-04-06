package com.example.MediSync.Services;

import com.example.MediSync.Entity.Assistant;
import com.example.MediSync.Entity.Doctor;
import com.example.MediSync.exceptions.UserException;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public interface AssistantService {
    public Assistant addAssistant(Assistant assistant) throws UserException;

    public Assistant findById(Long assistantId) throws UserException;

    public Assistant findUserProfileByJwt(String jwt) throws UserException;

    public Assistant updateUser(String jwt,Assistant assistant) throws UserException;
}
