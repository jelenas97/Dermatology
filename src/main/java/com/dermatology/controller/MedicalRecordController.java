package com.dermatology.controller;

import com.dermatology.model.MedicalRecord;
import com.dermatology.model.MedicalRecordDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(path = "/medicalRecord")
public class MedicalRecordController {

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("medicalRecordDto", new MedicalRecordDto());
        return "createMedicalRecord";
    }

    @GetMapping("/all")
    public ModelAndView show(Model model) {
        MedicalRecordDto medicalRecordDto = new MedicalRecordDto();
        medicalRecordDto.setFirstName("Miko");
        medicalRecordDto.setLastName("Mikic");
        medicalRecordDto.setDateOfBirth("13.05.1693");
        medicalRecordDto.setGender("Male");

        MedicalRecordDto medicalRecordDto1 = new MedicalRecordDto();
        medicalRecordDto1.setFirstName("Ziko");
        medicalRecordDto1.setLastName("Zikic");
        medicalRecordDto1.setDateOfBirth("23.10.1777");
        medicalRecordDto1.setGender("Male");

        List<MedicalRecordDto> medicalRecordDtoList = new ArrayList<>();
        medicalRecordDtoList.add(medicalRecordDto);
        medicalRecordDtoList.add(medicalRecordDto1);

        model.addAttribute("list",medicalRecordDtoList);

        return new ModelAndView("showMedicalRecords",model.asMap());
    }

    @PostMapping("/save")
    public void save( @Valid @ModelAttribute("medicalRecordDto") MedicalRecordDto medicalRecordDto){
        System.out.println(medicalRecordDto.getFirstName());

    }
}
