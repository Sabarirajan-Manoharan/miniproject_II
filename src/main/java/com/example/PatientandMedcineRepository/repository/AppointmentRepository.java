package com.example.PatientandMedcineRepository.repository;

import com.example.PatientandMedcineRepository.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}