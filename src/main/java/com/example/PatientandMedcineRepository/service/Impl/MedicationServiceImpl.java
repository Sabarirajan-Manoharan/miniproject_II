package com.example.PatientandMedcineRepository.service.Impl;

import com.example.PatientandMedcineRepository.entity.Medication;
import com.example.PatientandMedcineRepository.entity.Patient;
import com.example.PatientandMedcineRepository.repository.MedicationRespository;
import com.example.PatientandMedcineRepository.service.MedicationService;
import com.example.PatientandMedcineRepository.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MedicationServiceImpl implements MedicationService {

    private MedicationRespository medicationRepository;
    private PatientService patientService;

    @Override
    public List<Medication> getMedicationsByPatient(Long patientId) {
        Patient patient = patientService.findPatientById(patientId);
        return medicationRepository.findAllByPatient(patient);
        //return null;
    }

    @Override
    public Medication addMedication(Long patientId, String medicationName, String dosage, String frequency) {
        Patient patient = patientService.findPatientById(patientId); // Ensure patient exists
        if (patient == null) {
            throw new IllegalArgumentException("Invalid patient ID: " + patientId);
        }

        Medication medication = new Medication();
        medication.setPatient(patient);
        medication.setMedicationName(medicationName);
        medication.setDosage(dosage);
        medication.setFrequency(frequency);

        return medicationRepository.save(medication);
    }

    @Override
    public Medication editMedication(Long medicationId, String name, String dosage, String frequency) {
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid medication ID"));

        medication.setMedicationName(name);
        medication.setDosage(dosage);
        medication.setFrequency(frequency);

        return medicationRepository.save(medication);
    }

    @Override
    public void deleteMedication(Long medicationId) {
        medicationRepository.deleteById(medicationId);
    }


}
