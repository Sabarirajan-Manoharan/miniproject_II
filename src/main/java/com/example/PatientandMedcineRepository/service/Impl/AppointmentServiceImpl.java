package com.example.PatientandMedcineRepository.service.Impl;

import com.example.PatientandMedcineRepository.entity.Appointment;
import com.example.PatientandMedcineRepository.entity.Doctor;
import com.example.PatientandMedcineRepository.entity.Patient;
import com.example.PatientandMedcineRepository.repository.AppointmentRepository;
import com.example.PatientandMedcineRepository.service.AppointmentService;
import com.example.PatientandMedcineRepository.service.DoctorService;
import com.example.PatientandMedcineRepository.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository appointmentRepository;
    private DoctorService doctorService;
    private PatientService patientService;

    @Override
    public Appointment bookAppointment(Long doctorId, Long patientId, LocalDateTime appointmentDate) {
        Doctor doctor = doctorService.findDoctorById(doctorId);
        Patient patient = patientService.findPatientById(patientId);

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(appointmentDate);

        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> findAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findAll().stream()
                .filter(appointment -> appointment.getPatient().getId().equals(patientId))
                .collect(Collectors.toList());
    }
}
