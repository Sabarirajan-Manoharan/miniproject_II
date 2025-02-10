package com.example.PatientandMedcineRepository.service;

import com.example.PatientandMedcineRepository.dto.PatientDto;
import com.example.PatientandMedcineRepository.entity.Patient;

import java.util.List;

public interface PatientService {

    PatientDto registerPatient(PatientDto patientDto);
    Patient findPatientById(Long patientId);

}
