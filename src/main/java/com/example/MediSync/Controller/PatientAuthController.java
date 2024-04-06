package com.example.MediSync.Controller;

import com.example.MediSync.Repository.PatientRepository;
import com.example.MediSync.Request.LoginRequest;
import com.example.MediSync.Responces.AuthResponse;
import com.example.MediSync.Services.CustomePatientServiceImplementation;
import com.example.MediSync.Services.PatientService;
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
@RequestMapping("/auth/patient")
public class PatientAuthController {

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
    
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) throws UserException {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = Authentication(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateTokenForPatient(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("SignIn Successfull");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }


    private Authentication Authentication(String email, String password) {
        UserDetails userDetails = customePatientService.loadUserByUsername(email);

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
