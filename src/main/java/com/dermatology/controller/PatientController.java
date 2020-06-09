package com.dermatology.controller;

import com.dermatology.model.Medication;
import com.dermatology.model.Patient;
import com.dermatology.service.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getPatients() {
        List<Patient> patients= this.patientService.getAll();
        return new ResponseEntity( patients, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> getPatient(@PathVariable("id") String id) {
        Patient patient= this.patientService.getById(Long.parseLong(id));
        return new ResponseEntity(patient, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addPatient(@RequestBody Patient patient) {
        try {
            this.patientService.save(patient);
            return new ResponseEntity(HttpStatus.OK);
        } catch(Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
