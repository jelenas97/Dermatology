package com.dermatology.controller;

import com.dermatology.model.Patient;
import com.dermatology.model.Symptom;
import com.dermatology.service.interfaces.PatientService;
import com.dermatology.service.interfaces.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "symptom")
public class SymptomController {
    @Autowired
    private SymptomService symptomService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getSymptoms() {
        List<Symptom> symptoms= this.symptomService.getAll();
        return new ResponseEntity( symptoms, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> getSymptom(@PathVariable("id") String id) {
        Symptom symptom= this.symptomService.getById(Long.parseLong(id));
        return new ResponseEntity(symptom, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addSymptom(@RequestBody Symptom symptom) {
        try {
            this.symptomService.save(symptom);
            return new ResponseEntity(HttpStatus.OK);
        } catch(Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
