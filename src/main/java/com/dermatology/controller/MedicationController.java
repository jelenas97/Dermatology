package com.dermatology.controller;

import com.dermatology.model.AdditionalExam;
import com.dermatology.model.Medication;
import com.dermatology.service.interfaces.AdditionalExamService;
import com.dermatology.service.interfaces.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "medication")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getMedications() {
        List<Medication> medications= this.medicationService.getAll();
        return new ResponseEntity( medications, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> getMedication(@PathVariable("id") String id) {
        Medication medication= this.medicationService.getById(Long.parseLong(id));
        return new ResponseEntity(medication, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addMedication(@RequestBody Medication medication) {
        try {
            this.medicationService.save(medication);
            return new ResponseEntity(HttpStatus.OK);
        } catch(Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
