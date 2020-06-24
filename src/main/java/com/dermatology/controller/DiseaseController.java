package com.dermatology.controller;

import com.dermatology.cbr.CbrApplication;
import com.dermatology.dto.DiseaseDto;
import com.dermatology.dto.ExamDTO;
import com.dermatology.model.Disease;
import com.dermatology.model.Exam;
import com.dermatology.model.Patient;
import com.dermatology.model.PatientDescription;
import com.dermatology.service.interfaces.DiseaseService;
import com.dermatology.service.interfaces.ExamService;
import com.dermatology.service.interfaces.PatientService;
import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "disease")
public class DiseaseController {

    @Autowired
    private ExamService examService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DiseaseService diseaseService;


    @PostMapping("/predict/{patientId}")
    public ModelAndView predict(Model model, @Valid @ModelAttribute("diseaseDto")DiseaseDto diseaseDto, @PathVariable String patientId){
        //public void predict(@RequestBody DiseaseDto diseaseDto, @PathVariable String patientId){

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

            List<String> symptoms = Arrays.stream(diseaseDto.getSymptom().split(",")).collect(Collectors.toList());
            List<String> additionalExams = Arrays.stream(diseaseDto.getAdditionalExam().split(",")).collect(Collectors.toList());
            patientDescription.setSymptom(symptoms);
            patientDescription.setAdditionalExams(additionalExams);
            query.setDescription(patientDescription);

            application.cycle(query);
            application.preCycle();

            Collection<RetrievalResult> cases = application.predict();
            List<ExamDTO> foundCasesDTO = new ArrayList<>();
            List<String> diseases = new ArrayList<>();
            DecimalFormat df = new DecimalFormat("####0.000");

            int i = 1;
            for (RetrievalResult res : cases) {
                String[] s = res.get_case().getDescription().toString().split("caseId=");
                Long id = Long.parseLong(s[1].split("}")[0]);
                Exam e2 = this.examService.find(id);

                if (!diseases.contains(e2.getDisease().getName())) {
                    diseases.add(e2.getDisease().getName());
                    foundCasesDTO.add(new ExamDTO(e2, Double.parseDouble(df.format(res.getEval()))));
                }

                model.addAttribute("foundCases", foundCasesDTO);

            }
            int i2 = 1;
            for (ExamDTO e : foundCasesDTO) {
                System.out.println(i2 + " . pronadjeni slucaj je : " + e.toString());
                i2++;
            }


        } catch (Exception e) {
            return null;
        }
        //foundCasesDTO
        return new ModelAndView("showDiseasePrediction", model.asMap());
    }

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
        //JIPQuery query = engine.openSynchronousQuery("dijagnoze_preko_simptoma([H|T],[papule])");

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

    @GetMapping()
    public List<String> getAll() {

        List<String> disease = this.diseaseService.findDistinct();
        return disease;
    }

}
