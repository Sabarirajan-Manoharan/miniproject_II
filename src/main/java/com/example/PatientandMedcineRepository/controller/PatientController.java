package com.example.PatientandMedcineRepository.controller;

import com.example.PatientandMedcineRepository.dto.PatientDto;
import com.example.PatientandMedcineRepository.entity.Patient;
import com.example.PatientandMedcineRepository.service.PatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class PatientController {

    private PatientService patientService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "register";
    }

    @PostMapping
    public String registerPatient(@ModelAttribute PatientDto patientDto) {
        patientService.registerPatient(patientDto);
        return "redirect:/register?success";
    }

}
