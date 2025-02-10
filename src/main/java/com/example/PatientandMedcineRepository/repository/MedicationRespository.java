package com.example.PatientandMedcineRepository.repository;

import com.example.PatientandMedcineRepository.entity.Medication;
import com.example.PatientandMedcineRepository.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRespository extends JpaRepository<Medication, Long> {

   List<Medication> findAllByPatient(Patient patient);
}

