package com.example.PatientandMedcineRepository.service;

import com.example.PatientandMedcineRepository.entity.Medication;

import java.util.List;

public interface MedicationService {

    List<Medication> getMedicationsByPatient(Long patientId);
    Medication addMedication(Long patientId, String name, String dosage, String frequency);
    Medication editMedication(Long medicationId, String name, String dosage, String frequency);
    void deleteMedication(Long medicationId);

}
