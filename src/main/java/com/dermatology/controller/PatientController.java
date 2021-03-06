package com.dermatology.controller;

import com.dermatology.dto.PatientDto;
import com.dermatology.model.Exam;
import com.dermatology.model.Medication;
import com.dermatology.model.Patient;
import com.dermatology.service.interfaces.ExamService;
import com.dermatology.service.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private ExamService examService;

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("patientDto", new PatientDto());
        return "createPatient";
    }

    @GetMapping
    public ModelAndView show(Model model) {

        List<Patient> patients = patientService.getAll();
        List<PatientDto> patientDtoList = new ArrayList<>();

        for (int i = patients.size()-100; i<patients.size(); i++) {
            PatientDto newPatientDto = new PatientDto();
            newPatientDto.setId(patients.get(i).getId());
            newPatientDto.setFirstName(patients.get(i).getFirstName());
            newPatientDto.setLastName(patients.get(i).getLastName());
            newPatientDto.setGender(patients.get(i).getGender());
            newPatientDto.setAge(patients.get(i).getAge());
            patientDtoList.add(newPatientDto);
        }

        model.addAttribute("list", patientDtoList);

        return new ModelAndView("showPatients", model.asMap());
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> getPatient(@PathVariable("id") String id) {
        Patient patient= this.patientService.find(Long.parseLong(id));
        return new ResponseEntity(patient, HttpStatus.OK);
    }

    @PostMapping
    public ModelAndView addPatient(@Valid @ModelAttribute("patientDto") PatientDto patientDto, Model model) {

            Patient patient = new Patient();
            patient.setFirstName(patientDto.getFirstName());
            patient.setLastName(patientDto.getLastName());
            patient.setAge(patientDto.getAge());
            patient.setGender(patientDto.getGender());
            this.patientService.save(patient);

            List<Patient> patients = patientService.getAll();
            List<PatientDto> patientDtoList = new ArrayList<>();

            for(Patient p : patients) {
                PatientDto newPatientDto = new PatientDto();
                newPatientDto.setId(p.getId());
                newPatientDto.setFirstName(p.getFirstName());
                newPatientDto.setLastName(p.getLastName());
                newPatientDto.setGender(p.getGender());
                newPatientDto.setAge(p.getAge());
                patientDtoList.add(newPatientDto);
            }

            model.addAttribute("list", patientDtoList);

            return new ModelAndView("showPatients", model.asMap());


    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getPatients() {
        List<Patient> patients= this.patientService.getAll();
        return new ResponseEntity( patients, HttpStatus.OK);
    }

    @GetMapping("/moreInfo/{patientId}")
    public ModelAndView getPatientInfo(@PathVariable String patientId, Model model) {
        Patient patient= this.patientService.getById(Long.parseLong(patientId));

        List<Exam> exams = examService.getExamForPatient(Long.parseLong(patientId));

        System.out.println(exams);

        model.addAttribute("patient", patient);
        model.addAttribute("exams", exams);

        return new ModelAndView("moreInfo", model.asMap());
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
