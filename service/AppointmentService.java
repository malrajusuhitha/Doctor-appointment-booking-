package com.geekster.DoctorsAppointmentApplication.service;

import com.geekster.DoctorsAppointmentApplication.model.Appointment;
import com.geekster.DoctorsAppointmentApplication.repository.IAppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private IAppointmentRepo appointmentRepo;
    @Transactional
    public void bookAppointment(Appointment appointment) {
        Optional<Appointment> existing = appointmentRepo.findByDoctorAndTime(
                appointment.getDoctor(), appointment.getTime()
        );
        if (existing.isPresent()) {
            throw new IllegalStateException("Appointment slot is already booked.");
        }
        appointmentRepo.save(appointment);
    }

    public void cancelAppointment(Long appointmentId) {
        appointmentRepo.deleteById(appointmentId);
    }

    public Optional<Appointment> getAppointmentById(Long appointmentId) {
        return appointmentRepo.findById(appointmentId);
    }
}

