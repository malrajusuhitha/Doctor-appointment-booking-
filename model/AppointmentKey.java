package com.geekster.DoctorsAppointmentApplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class AppointmentKey implements Serializable {

    @Column(name = "appointment_id")
    private Long appointmentId;

    @Column(name = "appointment_time")
    private LocalDateTime time;

    public AppointmentKey() {}

    public AppointmentKey(Long appointmentId, LocalDateTime time) {
        this.appointmentId = appointmentId;
        this.time = time;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppointmentKey)) return false;
        AppointmentKey that = (AppointmentKey) o;
        return Objects.equals(appointmentId, that.appointmentId) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId, time);
    }
}
