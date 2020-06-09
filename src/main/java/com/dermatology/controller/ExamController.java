package com.dermatology.controller;


import com.dermatology.dto.AdditionalExamDto;
import com.dermatology.dto.DiseaseDto;
import com.dermatology.dto.MedicamentDto;
import com.dermatology.service.interfaces.AdditionalExamService;
import com.dermatology.service.interfaces.DiseaseService;
import com.dermatology.service.interfaces.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "exam")
public class ExamController {

    @Autowired
    private SymptomService symptomService;

    @Autowired
    private AdditionalExamService additionalExamService;

    @Autowired
    private DiseaseService diseaseService;

    @GetMapping("/new/{id}")
    public String create(Model model, @PathVariable String id) {

        DiseaseDto diseaseDto = new DiseaseDto();
        MedicamentDto medicamentDto = new MedicamentDto();
        AdditionalExamDto additionalExamDto = new AdditionalExamDto();

        diseaseDto.setPatientId(Long.parseLong(id));
        medicamentDto.setPatientId(Long.parseLong(id));
        additionalExamDto.setPatientId(Long.parseLong(id));

        List<String> symptoms = symptomService.findDistinct();
        List<String> additionalExams = additionalExamService.findDistinct();
        List<String> diseases = diseaseService.findDistinct();

        model.addAttribute("symptoms", symptoms);
        model.addAttribute("diseases", diseases);
        model.addAttribute("additionalExams", additionalExams);
        model.addAttribute("diseaseDto", diseaseDto);
        model.addAttribute("medicamentDto", medicamentDto);
        model.addAttribute("additionalExamDto", additionalExamDto);
        return "medicalExam";
    }
}
