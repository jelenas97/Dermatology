package com.dermatology.controller;


import com.dermatology.dto.AdditionalExamDto;
import com.dermatology.dto.DiseaseDto;
import com.dermatology.dto.MedicamentDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "exam")
public class ExamController {

    @GetMapping("/new/{id}")
    public String create(Model model, @PathVariable String id) {

        DiseaseDto diseaseDto = new DiseaseDto();
        MedicamentDto medicamentDto = new MedicamentDto();
        AdditionalExamDto additionalExamDto = new AdditionalExamDto();

        diseaseDto.setPatientId(Long.parseLong(id));
        medicamentDto.setPatientId(Long.parseLong(id));
        additionalExamDto.setPatientId(Long.parseLong(id));

        model.addAttribute("diseaseDto", diseaseDto);
        model.addAttribute("medicamentDto", medicamentDto);
        model.addAttribute("additionalExamDto", additionalExamDto);
        return "medicalExam";
    }
}
