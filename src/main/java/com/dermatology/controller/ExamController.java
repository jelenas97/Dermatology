package com.dermatology.controller;


import com.dermatology.dto.AdditionalExamDto;
import com.dermatology.dto.DiseaseDto;
import com.dermatology.dto.MedicamentDto;
import com.dermatology.cbr.CbrApplication;
import com.dermatology.dto.ExamDTO;
import com.dermatology.model.Exam;
import com.dermatology.model.Patient;
import com.dermatology.model.PatientDescription;
import com.dermatology.service.interfaces.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private PatientService patientService;



    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity newCase(@RequestBody ExamDTO examDTO) {
    //public ResponseEntity newCase() {
        try {

            System.out.println("Dobijam dto" + examDTO);

            List<Exam> examCases = this.examService.findAll();

            CbrApplication application = new CbrApplication(examCases);

            application.configure();
            application.preCycle();
            CBRQuery query = new CBRQuery();

            Patient p = this.patientService.find(examDTO.getPatientId());

            PatientDescription patientDescription = new PatientDescription();
            patientDescription.setAge(p.getAge());
            patientDescription.setGender(p.getGender());

            patientDescription.setSymptom(examDTO.getSymptomList());

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
                System.out.println(i + " . pronadjeni slucaj je : " + res);
                i++;
            }
            int i2 = 1;
            for(Exam e : foundCases){
                //    System.out.println( i + " . pronadjeni slucaj je : " + e.toString());
                i2++;
            }

        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }


    @GetMapping("/new/{id}")
    public String create(Model model, @PathVariable String id) {

        DiseaseDto diseaseDto = new DiseaseDto();
        MedicamentDto medicamentDto = new MedicamentDto();
        AdditionalExamDto additionalExamDto = new AdditionalExamDto();

        diseaseDto.setPatientId(Long.parseLong(id));
        medicamentDto.setPatientId(Long.parseLong(id));
        additionalExamDto.setPatientId(Long.parseLong(id));

        model.addAttribute("diseaseDto", diseaseDto);
        model.addAttribute("medicamentDto", medicamentDto);
        model.addAttribute("additionalExamDto", additionalExamDto);
        return "medicalExam";
    }
}
