package com.example.PatientandMedcineRepository.service.Impl;

import com.example.PatientandMedcineRepository.entity.Doctor;
import com.example.PatientandMedcineRepository.repository.DoctorRepository;
import com.example.PatientandMedcineRepository.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor findDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor not found"));
    }
}
