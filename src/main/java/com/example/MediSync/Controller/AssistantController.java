package com.example.MediSync.Controller;

import com.example.MediSync.Entity.Assistant;
import com.example.MediSync.Entity.Doctor;
import com.example.MediSync.Request.LoginRequest;
import com.example.MediSync.Responces.AuthResponse;
import com.example.MediSync.Services.AssistantService;
import com.example.MediSync.Services.CustomeAssistantServiceImplementation;
import com.example.MediSync.config.JWTProvider;
import com.example.MediSync.exceptions.UserException;
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

@RestController
@RequestMapping("/api/assistant")
public class AssistantController {

    @Autowired
    private AssistantService assistantService;

    @GetMapping("/profile")
    public ResponseEntity<Assistant> getUserProfileHandler(@RequestHeader("Authorization") String jwt)throws UserException {
        Assistant user = assistantService.findUserProfileByJwt(jwt);
        return new ResponseEntity<Assistant>(user, HttpStatus.ACCEPTED);
    }

    @PutMapping("/profile/update")
    public ResponseEntity<Assistant> updateUser(@RequestHeader("Authorization") String jwt,@RequestBody Assistant assistant)throws UserException {
        Assistant updatedProfile = assistantService.updateUser(jwt,assistant);
        return new ResponseEntity<Assistant>(updatedProfile,HttpStatus.ACCEPTED);
    }
}
