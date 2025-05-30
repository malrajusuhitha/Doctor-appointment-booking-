package com.geekster.DoctorsAppointmentApplication.controller;

import com.geekster.DoctorsAppointmentApplication.model.Appointment;
import com.geekster.DoctorsAppointmentApplication.model.Doctor;
import com.geekster.DoctorsAppointmentApplication.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/doctors")  // add /api prefix
public class DoctorController {

    @Autowired
    DoctorService docService;

    @PostMapping("/add")  // matches POST /api/doctors/add
    public void addDoctor(@RequestBody Doctor doctor){
        docService.addDoctor(doctor);
    }

    @GetMapping("/all")
    public List<Doctor> getAllDoctors() {
        return docService.getAllDoctors();
    }


    @GetMapping("/appointments/{docId}")  // matches GET /api/doctors/appointments/{doctorId}
    public ResponseEntity<List<Appointment>> getDocMyAppointments(@PathVariable Long docId) {
        List<Appointment> myAppointments = null;
        HttpStatus status;
        try {
            myAppointments = docService.getMyAppointments(docId);
            if(myAppointments.isEmpty()) {
                status = HttpStatus.NO_CONTENT;
            } else {
                status = HttpStatus.OK;
            }
        } catch(Exception e) {
            System.out.println("The doc Id is not valid");
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(myAppointments,status);
    }
}

