package com.example.PatientandMedcineRepository.service.Impl;

import com.example.PatientandMedcineRepository.dto.PatientDto;
import com.example.PatientandMedcineRepository.entity.Patient;
import com.example.PatientandMedcineRepository.repository.PatientRepository;
import com.example.PatientandMedcineRepository.service.PatientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public PatientDto registerPatient(PatientDto patientDto) {
        Patient patient1 =new Patient();
        patient1.setName(patientDto.getName());
        patient1.setPassword(passwordEncoder.encode(patientDto.getPassword()));
        patient1.setMedicalHistory(patientDto.getMedicalHistory());
        patient1.setContactDetails(patientDto.getContactDetails());

        patient1 = patientRepository.save(patient1);

        return new PatientDto(patient1.getId(),patient1.getName(), patient1.getPassword(), patient1.getContactDetails(), patient1.getMedicalHistory(), patient1.getEmail());
    }


    @Override
    public Patient findPatientById(Long patientId) {
        return patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found"));
    }
}
