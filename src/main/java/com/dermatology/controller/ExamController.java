package com.dermatology.controller;


import com.dermatology.cbr.CbrApplication;
import com.dermatology.model.Exam;
import com.dermatology.model.PatientDescription;
import com.dermatology.service.interfaces.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping(value = "exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    //   public ResponseEntity newCase(@RequestBody ExamDTO examDTO) {
    public ResponseEntity newCase() {
        try {

            List<Exam> examCases = this.examService.findAll();

            CbrApplication application = new CbrApplication(examCases);

            //Patient p =  this.pat examDTO.getPatientId()
            System.out.println("RAdi");
            application.configure();
            application.preCycle();
            CBRQuery query = new CBRQuery();
            PatientDescription patientDescription = new PatientDescription();


            patientDescription.setAge(58);
            patientDescription.setGender("Zensko");
            List<String> symptoms = new ArrayList<String>();
            symptoms.add("papule");
            symptoms.add("crvenilo");
            symptoms.add("svrab");

            patientDescription.setSymptom(symptoms);

            query.setDescription(patientDescription);


            application.cycle(query);
            application.preCycle();

            Collection<RetrievalResult> cases = application.predict();

            for (RetrievalResult res : cases) {
                System.out.println("Predvidjamo" + res.toString());
            }

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return null;
    }

}
