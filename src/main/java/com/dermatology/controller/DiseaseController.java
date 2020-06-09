package com.dermatology.controller;

import com.dermatology.model.Disease;
import com.dermatology.service.interfaces.DiseaseService;
import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "disease")
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getDiseases() {
        List<Disease> diseases= this.diseaseService.getAll();
        return new ResponseEntity(diseases, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> getDisease(@PathVariable("id") String id) {
        Disease disease= this.diseaseService.getById(Long.parseLong(id));
        return new ResponseEntity(disease, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addDisease(@RequestBody Disease disease) {
        try {
            this.diseaseService.save(disease);
            return new ResponseEntity(HttpStatus.OK);
        } catch(Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping(path = "/prolog")
    public ResponseEntity<?> test() {

        JIPEngine engine = new JIPEngine();

        engine.consultFile("src/main/java/com/dermatology/data/program.pl");
        JIPQuery query = engine.openSynchronousQuery("lekovi(L,akne)");

        // pravila se mogu dodavati i tokom izvrsavanja (u runtime-u)
        // assertz dodaje pravilo na kraj programa (aasserta dodaje na pocetak programa), na primer:
        // engine.assertz(engine.getTermParser().parseTerm("sledbenik(X,Y) :- X is Y+1."));

        JIPTerm solution;
        while ( (solution = query.nextSolution()) != null) {
            System.out.println("solution: " + solution);
            System.out.println("unbounded: " +  solution.getUnboundedVariables().toString());
            for (JIPVariable var: solution.getVariables()) {
                String result = var.getValue().toString();
                result = result.replace("'", "");
                result = result.replace(".", "");
                result = result.replace("(", "");
                result = result.replace(")", "");
                result = result.replace("[", "");
                result = result.replace("]", "");
                result = result.replace(",,", ",");

                String[] additionalExams = result.split(",");
                System.out.println(additionalExams);

            }
        }        return new ResponseEntity(HttpStatus.OK);

    }

}
