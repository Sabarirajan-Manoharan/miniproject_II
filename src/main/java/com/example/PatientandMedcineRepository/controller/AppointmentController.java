package com.example.PatientandMedcineRepository.controller;

import com.example.PatientandMedcineRepository.entity.Appointment;
import com.example.PatientandMedcineRepository.entity.Doctor;
import com.example.PatientandMedcineRepository.entity.Patient;
import com.example.PatientandMedcineRepository.repository.AppointmentRepository;
import com.example.PatientandMedcineRepository.repository.DoctorRepository;
import com.example.PatientandMedcineRepository.repository.PatientRepository;
import com.example.PatientandMedcineRepository.service.AppointmentService;
import com.example.PatientandMedcineRepository.service.DoctorService;
import com.example.PatientandMedcineRepository.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;

    private DoctorService doctorService;

    private PatientRepository patientRepository;

    @GetMapping
    public String viewAppointments(@RequestParam(required = false) Long patientId, Model model, Principal principal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        Patient patient = patientRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        model.addAttribute("pid", patient.getId());
        model.addAttribute("username", username);
        if (patientId != null) {
            model.addAttribute("appointments", appointmentService.findAppointmentsByPatientId(patientId));
        } else {
            model.addAttribute("appointments", List.of());
        }
        model.addAttribute("doctors", doctorService.findAllDoctors()); // To show available doctors
        return "appointments";
    }
    @PostMapping("/book")
    public String bookAppointment(@RequestParam Long doctorId,
                                  @RequestParam(required = false) Long patientId,
                                  @RequestParam String appointmentDate,
                                  Principal principal) {
        if (patientId == null) {
            String username = principal.getName();
            Patient patient = patientRepository.findByName(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            patientId = patient.getId();
        }

        appointmentService.bookAppointment(doctorId, patientId, LocalDateTime.parse(appointmentDate));
        return "redirect:/appointments?patientId=" + patientId;
    }
}
