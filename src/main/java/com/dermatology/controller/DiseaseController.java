package com.dermatology.controller;

import com.dermatology.dto.DiseaseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "disease")
public class DiseaseController {

    @PostMapping("/predict/{patientId}")
    public String predict(Model model, @Valid @ModelAttribute("diseaseDto")DiseaseDto diseaseDto, @PathVariable String patientId){
        System.out.println(diseaseDto.getSymptom());
        return "hello";
    }
}
