package com.example.PatientandMedcineRepository.controller;


import com.example.PatientandMedcineRepository.entity.Medication;
import com.example.PatientandMedcineRepository.entity.Patient;
import com.example.PatientandMedcineRepository.repository.MedicationRespository;
import com.example.PatientandMedcineRepository.repository.PatientRepository;
import com.example.PatientandMedcineRepository.service.MedicationService;
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
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/medications")
public class MedicationController {


    private MedicationService medicationService;
    private PatientRepository patientRepository;

    @GetMapping
    public String viewMedications(@RequestParam(required = false)  Long patientId, Model model, Principal principal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        Patient patient = patientRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        model.addAttribute("pid", patient.getId());
        model.addAttribute("username", username);
        if (patientId != null) {
            model.addAttribute("medications", medicationService.getMedicationsByPatient(patientId));
        } else {
            model.addAttribute("medications",  List.of()); // To use in the form for adding new medications
        }
        model.addAttribute("patientId", patientId); // To use in the form for adding new medications
        return "medications"; // Corresponds to `medications.html` in Thymeleaf templates
    }

    @PostMapping("/add")
    public String addMedication(@RequestParam(required = false) Long patientId,
                                @RequestParam String name,
                                @RequestParam String dosage,
                                @RequestParam String frequency,
                                Principal principal) {
        if (patientId == null) {
            String username = principal.getName();
            Patient patient = patientRepository.findByName(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            patientId = patient.getId();
        }

        medicationService.addMedication(patientId, name, dosage, frequency);
        return "redirect:/medications?patientId=" + patientId;
    }

    @PostMapping("/edit")
    public String editMedication(@RequestParam Long medicationId,
                                 @RequestParam String name,
                                 @RequestParam String dosage,
                                 @RequestParam String frequency,
                                 @RequestParam Long patientId) {
        medicationService.editMedication(medicationId, name, dosage, frequency);
        return "redirect:/medications?patientId=" + patientId;
    }

    @PostMapping("/delete")
    public String deleteMedication(@RequestParam Long medicationId, @RequestParam Long patientId) {
        medicationService.deleteMedication(medicationId);
        return "redirect:/medications?patientId=" + patientId;
    }
}
