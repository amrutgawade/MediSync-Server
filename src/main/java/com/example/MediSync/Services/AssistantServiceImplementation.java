package com.example.MediSync.Services;

import com.example.MediSync.Entity.Assistant;
import com.example.MediSync.Repository.AssistantRepository;
import com.example.MediSync.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssistantServiceImplementation implements AssistantService{

    @Autowired
    private AssistantRepository assistantRepository;
    @Override
    public Assistant addAssistant(Assistant assistant) throws UserException {
        Assistant assistant1 = assistantRepository.findByEmail(assistant.getEmail());
        if(assistant1 != null){
            throw new UserException("Assistant Already Exists");
        }else{
            assistant.setRole("Assistant");
            return assistantRepository.save(assistant);
        }

    }
}
