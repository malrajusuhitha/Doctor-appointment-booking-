package com.geekster.DoctorsAppointmentApplication.controller;

import com.geekster.DoctorsAppointmentApplication.dto.SignInInput;
import com.geekster.DoctorsAppointmentApplication.dto.SignInOutput;
import com.geekster.DoctorsAppointmentApplication.dto.SignUpInput;
import com.geekster.DoctorsAppointmentApplication.dto.SignUpOutput;
import com.geekster.DoctorsAppointmentApplication.model.Doctor;
import com.geekster.DoctorsAppointmentApplication.service.AuthenticationService;
import com.geekster.DoctorsAppointmentApplication.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    AuthenticationService authService;

    @PostMapping("/signup")  // POST /api/patients/signup
    public SignUpOutput signUp(@RequestBody SignUpInput signUpDto){
        return patientService.signUp(signUpDto);
    }

    @PostMapping("/signin")  // POST /api/patients/signin
    public SignInOutput signIn(@RequestBody SignInInput signInDto){
        return patientService.signIn(signInDto);
    }

    @GetMapping("/doctors")  // GET /api/patients/doctors?userEmail=...&token=...
    public ResponseEntity<List<Doctor>> getAllDoctors(@RequestParam String userEmail,
                                                      @RequestParam String token){
        HttpStatus status;
        List<Doctor> allDoctors = null;

        if(authService.authenticate(userEmail,token)) {
            allDoctors = patientService.getAllDoctors();
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<>(allDoctors, status);
    }
}
