package com.geekster.DoctorsAppointmentApplication.repository;


import com.geekster.DoctorsAppointmentApplication.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenRepo extends JpaRepository<AuthenticationToken, Long> {




    AuthenticationToken findFirstByToken(String token);
}
