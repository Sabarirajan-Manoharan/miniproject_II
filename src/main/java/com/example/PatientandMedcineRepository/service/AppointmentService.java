package com.example.PatientandMedcineRepository.service;

import com.example.PatientandMedcineRepository.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    Appointment bookAppointment(Long doctorId, Long patientId, LocalDateTime appointmentDate);
    List<Appointment> findAppointmentsByPatientId(Long patientId);

}
