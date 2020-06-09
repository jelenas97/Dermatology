package com.dermatology.controller;


import com.dermatology.service.interfaces.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "symptom")
public class SymptomController {
    @Autowired
    private SymptomService symptomService;

    @GetMapping()
    public List<String> getAll() {

        List<String> symptoms = this.symptomService.findDistinct();
        return symptoms;
    }

}