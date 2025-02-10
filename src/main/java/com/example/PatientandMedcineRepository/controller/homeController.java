package com.example.PatientandMedcineRepository.controller;

import com.example.PatientandMedcineRepository.entity.Patient;
import com.example.PatientandMedcineRepository.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class homeController {

    private PatientRepository patientRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String admin(Model model, Principal principal) {
        // Get the logged-in user's username


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        Patient patient = patientRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String roles = authentication.getAuthorities().toString();
        model.addAttribute("username", username);
        model.addAttribute("id", patient.getId());
        return "home"; // This matches the Thymeleaf template: home.html
    }
}
