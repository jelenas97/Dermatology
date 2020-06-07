package com.dermatology.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/medicalRecord")
public class MedicalRecordController {

    @GetMapping("/new")
    public String newMedicalRecord() {
        return "createMedicalRecord";
    }
}
