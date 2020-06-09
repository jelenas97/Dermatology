package com.dermatology.controller;

import com.dermatology.dto.DiseaseDto;
import com.dermatology.dto.MedicamentDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "medicament")
public class MedicamentController {


    @PostMapping("/predict/{patientId}")
    public String predict(Model model, @Valid @ModelAttribute("medicationDto") MedicamentDto medicamentDto, @PathVariable String patientId){
        System.out.println(medicamentDto.getDisease());
        return "hello";
    }

}
