package com.example.MediSync.Controller;

import com.example.MediSync.Entity.Doctor;
import com.example.MediSync.Services.DoctorService;
import com.example.MediSync.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;


    @GetMapping("/profile")
    public ResponseEntity<Doctor> getUserProfileHandler(@RequestHeader("Authorization") String jwt)throws UserException {
        Doctor user = doctorService.findUserProfileByJwt(jwt);
        return new ResponseEntity<Doctor>(user, HttpStatus.ACCEPTED);
    }

    @PutMapping("/profile/update")
    public ResponseEntity<Doctor> updateUser(@RequestHeader("Authorization") String jwt,@RequestBody Doctor doctor)throws UserException {
        Doctor updatedProfile = doctorService.updateUser(jwt,doctor);
        return new ResponseEntity<Doctor>(updatedProfile,HttpStatus.ACCEPTED);
    }
}
