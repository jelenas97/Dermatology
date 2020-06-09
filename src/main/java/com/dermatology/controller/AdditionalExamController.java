package com.dermatology.controller;

import com.dermatology.cbr.CbrApplication;
import com.dermatology.dto.AdditionalExamDto;
import com.dermatology.model.AdditionalExam;
import com.dermatology.model.Exam;
import com.dermatology.model.Patient;
import com.dermatology.model.PatientDescription;
import com.dermatology.service.interfaces.AdditionalExamService;
import com.dermatology.service.interfaces.ExamService;
import com.dermatology.service.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "additionalExam")
public class AdditionalExamController {

    @Autowired
    private AdditionalExamService additionalExamService;

    @Autowired
    private ExamService examService;

    @Autowired
    private PatientService patientService;


    @PostMapping("/predict/{patientId}")
    public String newCase(@ModelAttribute("additionalExamDto") AdditionalExamDto additionalExamDto, @PathVariable String patientId) {
        try {
            List<Exam> examCases = this.examService.findAll();

            CbrApplication application = new CbrApplication(examCases);

            application.configure();
            application.preCycle();
            CBRQuery query = new CBRQuery();

            Patient p = this.patientService.find(Long.parseLong(patientId));

            PatientDescription patientDescription = new PatientDescription();
            patientDescription.setAge(p.getAge());
            patientDescription.setGender(p.getGender());

            List<String> symptoms = Arrays.stream(additionalExamDto.getSymptoms().split(",")).collect(Collectors.toList());

            patientDescription.setSymptom(symptoms);

            query.setDescription(patientDescription);

            application.cycle(query);
            application.preCycle();

            Collection<RetrievalResult> cases = application.predict();
            List<Exam> foundCases = new ArrayList<>();
            int i = 1;
            for (RetrievalResult res : cases) {
                String[] s = res.get_case().getDescription().toString().split("caseId=");
                Long id = Long.parseLong(s[1].split("}")[0]);
                Exam e2 = this.examService.find(id);
                foundCases.add(e2);
                System.out.println(i + " . pronadjeni slucaj je : " + res.getEval());
                i++;
            }
            int i2 = 1;
            for(Exam e : foundCases){
                //    System.out.println( i + " . pronadjeni slucaj je : " + e.toString());
                i2++;
            }

        }catch(Exception e){
            return "bilo sta";
        }
        return "hello";
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAdditionalExams() {
        List<AdditionalExam> additionalExams= this.additionalExamService.getAll();
        return new ResponseEntity(additionalExams, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> getAdditionalExam(@PathVariable("id") String id) {
        AdditionalExam additionalExam= this.additionalExamService.getById(Long.parseLong(id));
        return new ResponseEntity(additionalExam, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addAdditionalExam(@RequestBody AdditionalExam additionalExam) {
        try {
            this.additionalExamService.save(additionalExam);
            return new ResponseEntity(HttpStatus.OK);
        } catch(Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
