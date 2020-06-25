package com.dermatology.controller;

import com.dermatology.cbr.CbrApplication;
import com.dermatology.dto.AdditionalExamDto;
import com.dermatology.dto.DiseaseDto;
import com.dermatology.dto.ExamDTO;
import com.dermatology.dto.MedicamentDto;
import com.dermatology.model.Exam;
import com.dermatology.model.Medication;
import com.dermatology.model.Patient;
import com.dermatology.model.PatientDescription;
import com.dermatology.service.interfaces.*;
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
@RequestMapping(value = "medicament")
public class MedicamentController {

    @Autowired
    private ExamService examService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicationService medicationService;

    @Autowired
    private DiseaseService diseaseService;

    @Autowired
    private SymptomService symptomService;

    @Autowired
    private AdditionalExamService additionalExamService;

    @PostMapping("/predict/{patientId}")
    public String predict(Model model, @Valid @ModelAttribute("medicationDto") MedicamentDto medicamentDto, @PathVariable String patientId){
//    public void predict(@RequestBody MedicamentDto medicamentDto, @PathVariable String patientId){
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

            patientDescription.setDisease(medicamentDto.getDisease());

            query.setDescription(patientDescription);

            application.cycle(query);
            application.preCycle();

            Collection<RetrievalResult> cases = application.predict();
            List<ExamDTO> foundCasesDTO = new ArrayList<>();
            List<String> medications = new ArrayList<>();
            DecimalFormat df = new DecimalFormat("####0.000");
            int i = 1;
            for (RetrievalResult res : cases) {
                String[] s = res.get_case().getDescription().toString().split("caseId=");
                Long id = Long.parseLong(s[1].split("}")[0]);
                Exam e2 = this.examService.find(id);
                ExamDTO dto = new ExamDTO(e2, res.getEval() * 100);
                if (!medications.contains(e2.getMedications().get(0).getName()) && medications.size() < 5) {
                    medications.add(e2.getMedications().get(0).getName());
                    foundCasesDTO.add(new ExamDTO(e2, Double.parseDouble(df.format(res.getEval() * 100))));
                }
                model.addAttribute("foundCases", foundCasesDTO);
                System.out.println(i + " . pronadjeni slucaj je : " + dto);

            }
            int i2 = 1;
            for (ExamDTO e : foundCasesDTO) {
                System.out.println(i2 + " . pronadjeni slucaj je : " + e.toString());
                i2++;
            }
        } catch (Exception e) {
            return null;
        }

        DiseaseDto diseaseDto = new DiseaseDto();
        AdditionalExamDto additionalExamDto = new AdditionalExamDto();
        diseaseDto.setPatientId(Long.parseLong(patientId));
        additionalExamDto.setPatientId(Long.parseLong(patientId));

        List<String> symptoms = symptomService.findDistinct();
        List<String> additionalExams = additionalExamService.findDistinct();
        List<String> diseases = diseaseService.findDistinct();

        model.addAttribute("symptoms", symptoms);
        model.addAttribute("diseases", diseases);
        model.addAttribute("additionalExams", additionalExams);
        model.addAttribute("diseaseDto", diseaseDto);
        model.addAttribute("medicamentDto", medicamentDto);
        model.addAttribute("additionalExamDto", additionalExamDto);
        model.addAttribute("exam", new ExamDTO());
        return "medicalExam";
    }

    @GetMapping()
    public List<String> getAll() {

        List<String> medications = this.medicationService.findDistinct();
        return medications;
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

    @PostMapping("/prolog/{patientId}")
    public ModelAndView predictProlog(Model model, @Valid @ModelAttribute("medicationDto") MedicamentDto medicamentDto, @PathVariable String patientId) {

        JIPEngine engine = new JIPEngine();

        engine.consultFile("src/main/java/com/dermatology/data/program.pl");

        List<String> diseases = Arrays.stream(medicamentDto.getDisease().split(",")).collect(Collectors.toList());
        String diseasesProlog = medicamentDto.getDisease();
        JIPQuery query;
        if (diseases.size() == 0) {
            return null;
        } else if (diseases.size() == 1)
        {

            query = engine.openSynchronousQuery("lekovi(L," + diseasesProlog + ")");

        } else
        {
            return null;
        }
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

                String[] medication = result.split(",");
                System.out.println(medication);

            }
        }
        return new ModelAndView("showDiseasePrediction", model.asMap());


    }

}
