package com.example.MediSync.Controller;

import com.example.MediSync.Entity.Doctor;
import com.example.MediSync.Repository.DoctorRepository;
import com.example.MediSync.Responces.AuthResponse;
import com.example.MediSync.Services.CustomUserServiceImplementation;
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
@RequestMapping("/auth")
public class AuthController {

    private DoctorRepository doctorRepository;
    private JWTProvider jwtProvider;
    private PasswordEncoder passwordEncoder;
    private CustomUserServiceImplementation customUserService;

    public AuthController() {
    }

    @Autowired
    public AuthController(DoctorRepository doctorRepository, JWTProvider jwtProvider, PasswordEncoder passwordEncoder, CustomUserServiceImplementation customUserService) {
        this.doctorRepository = doctorRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.customUserService = customUserService;

    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody Doctor doctor) throws UserException {
        String email = doctor.getEmail();
        String password = doctor.getPassword();
        String firstName = doctor.getFirst_Name();
        String lastName = doctor.getLast_name();
        Integer mobile = doctor.getMobile_No();
        String gender = doctor.getGender();

        System.out.println(doctor.toString());
        Doctor isEmailExist = doctorRepository.findByEmail(email);
        System.out.println(doctor.toString());
        if (isEmailExist != null) {
            throw new UserException("Email already exist for another user");
        }
        System.out.println(doctor.toString());
        Doctor createDoctor = new Doctor();
        createDoctor.setEmail(doctor.getEmail());
        createDoctor.setPassword(passwordEncoder.encode(password));
        createDoctor.setFirst_Name(firstName);
        createDoctor.setLast_name(lastName);
        createDoctor.setMobile_No(mobile);
        createDoctor.setGender(gender);
        createDoctor.setRole("Doctor");

        Doctor savedDoctor = doctorRepository.save(createDoctor);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedDoctor.getEmail(),savedDoctor.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(token);

        authResponse.setMessage("Signup Successfully");

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }
//    @PostMapping("/signin")
//    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) throws UserException{
//
//        String email = loginRequest.getEmail();
//        String password = loginRequest.getPassword();
//
//        Authentication authentication = Authentication(email,password);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String token = jwtProvider.generateToken(authentication);
//
//        AuthResponse authResponse = new AuthResponse();
//        authResponse.setJwt(token);
//        authResponse.setMessage("SignIn Successfull");
//
//        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
//    }
    private Authentication Authentication(String email, String password) {
        UserDetails userDetails = customUserService.loadUserByUsername(email);

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
