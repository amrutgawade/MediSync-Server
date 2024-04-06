package com.example.MediSync.Controller;

import com.example.MediSync.Entity.Assistant;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assistant")
public class AssistantController {

    private JWTProvider jwtProvider;
    @Autowired
    private AssistantService assistantService;

    private PasswordEncoder passwordEncoder;

    private CustomeAssistantServiceImplementation customeAssistantService;
    @PostMapping("/addassistant")
    public ResponseEntity<Assistant> addAssistant(@RequestBody Assistant assistant) throws UserException {
        Assistant newAssistant = assistantService.addAssistant(assistant);
        return new ResponseEntity<Assistant>(newAssistant, HttpStatus.CREATED);
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) throws UserException{

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = Authentication(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("SignIn Successfull");

        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
    }

    private Authentication Authentication(String email, String password) {
        UserDetails userDetails = customeAssistantService.loadUserByUsername(email);

        if (userDetails==null) {
            throw new BadCredentialsException("Invalid Email");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid Password");
        }
        System.out.println("Authorities:"+userDetails.getAuthorities());
        return new UsernamePasswordAuthenticationToken(email, null,userDetails.getAuthorities());
    }
}
