package com.geekster.DoctorsAppointmentApplication.controller;

import com.geekster.DoctorsAppointmentApplication.model.Appointment;
import com.geekster.DoctorsAppointmentApplication.service.AppointmentService;
import com.geekster.DoctorsAppointmentApplication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")  // use plural and add /api prefix
public class AppointmentController  {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    AuthenticationService authService;

    @PostMapping("/book")
    public ResponseEntity<String> bookAppointment(@RequestBody Appointment appointment){
        try{
            appointmentService.bookAppointment(appointment);
            return new ResponseEntity<>("Appointment booked successfully", HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("Book Another Appointment as this appointment is already booked", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<String> cancelAppointment(
            @RequestHeader("userEmail") String userEmail,
            @RequestHeader("token") String token,
            @RequestParam Long appointmentId) {

        if (authService.authenticate(userEmail, token)) {
            appointmentService.cancelAppointment(appointmentId);
            return ResponseEntity.ok("Appointment canceled successfully");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid user");
        }
    }

}

