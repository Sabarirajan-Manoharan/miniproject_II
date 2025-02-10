package com.example.PatientandMedcineRepository.service;

import com.example.PatientandMedcineRepository.entity.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> findAllDoctors();
    Doctor findDoctorById(Long doctorId);
}
