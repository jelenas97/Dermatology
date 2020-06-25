package com.dermatology.controller;


import com.dermatology.cbr.CbrApplication;
import com.dermatology.dto.AdditionalExamDto;
import com.dermatology.dto.DiseaseDto;
import com.dermatology.dto.ExamDTO;
import com.dermatology.dto.MedicamentDto;
import com.dermatology.model.*;
import com.dermatology.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping(value = "exam")
public class ExamController {

    @Autowired
    private SymptomService symptomService;

    @Autowired
    private AdditionalExamService additionalExamService;

    @Autowired
    private DiseaseService diseaseService;

    @Autowired
    private ExamService examService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicationService medicationService;


    @PostMapping("/{patientId}")
    public String save(@ModelAttribute("exam") ExamDTO examDTO, @PathVariable String patientId){
        Patient patient = patientService.getById(Long.parseLong(patientId));
        Disease disease = diseaseService.findByName(examDTO.getDiseaseExam());

        Exam exam = new Exam();
        List<Symptom> symptoms = new ArrayList<>();
        List<Medication> medications = new ArrayList<>();
        List<AdditionalExam> additionalExams = new ArrayList<>();

        for (String s : examDTO.getSymptomList()) {
            Symptom symptom = new Symptom();
            symptom.setName(s);
            symptoms.add(symptom);
            symptomService.save(symptom);
        }

        for (String s : examDTO.getMedications()) {
            Medication medication = new Medication();
            medication.setName(s);
            medications.add(medication);
            medicationService.save(medication);
        }

        for (String s : examDTO.getAdditionalExams()) {
            AdditionalExam additionalExam = new AdditionalExam();
            additionalExam.setName(s);
            additionalExams.add(additionalExam);
            additionalExamService.save(additionalExam);
        }

        exam.setPatient(patient);
        exam.setDisease(disease);
        exam.setMedications(medications);
        exam.setAdditionalExam(additionalExams);
        exam.setSymptomList(symptoms);

        examService.save(exam);

        return "hello";
    }


    @PostMapping
    public ResponseEntity newCase(@ModelAttribute("exam") ExamDTO examDTO) {

        System.out.println("eo");

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
            List<ExamDTO> foundCases = new ArrayList<>();
            List<String> diseases = new ArrayList<>();
            DecimalFormat df = new DecimalFormat("####0.000");
            for (RetrievalResult res : cases) {
                String[] s = res.get_case().getDescription().toString().split("caseId=");
                Long id = Long.parseLong(s[1].split("}")[0]);
                Exam e2 = this.examService.find(id);

                if (!diseases.contains(e2.getDisease().getName())) {
                    diseases.add(e2.getDisease().getName());
                    foundCases.add(new ExamDTO(e2, Double.parseDouble(df.format(res.getEval()))));
                }
            }
            int i2 = 1;
            for (ExamDTO e : foundCases) {
                System.out.println(i2 + " . pronadjeni slucaj je : " + e.toString());
                i2++;
            }

        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }


    @GetMapping("/new/{id}")
    public String create(Model model, @PathVariable String id) {

        ExamDTO examDTO = new ExamDTO();
        DiseaseDto diseaseDto = new DiseaseDto();
        MedicamentDto medicamentDto = new MedicamentDto();
        AdditionalExamDto additionalExamDto = new AdditionalExamDto();

        diseaseDto.setPatientId(Long.parseLong(id));
        medicamentDto.setPatientId(Long.parseLong(id));
        additionalExamDto.setPatientId(Long.parseLong(id));

        List<String> symptoms = symptomService.findDistinct();
        List<String> additionalExams = additionalExamService.findDistinct();
        List<String> diseases = diseaseService.findDistinct();
        List<String> medications = medicationService.findDistinct();

        model.addAttribute("exam", examDTO);
        model.addAttribute("symptoms", symptoms);
        model.addAttribute("diseases", diseases);
        model.addAttribute("diseaseDto", diseaseDto);
        model.addAttribute("medications", medications);
        model.addAttribute("medicamentDto", medicamentDto);
        model.addAttribute("patientId", Long.parseLong(id));
        model.addAttribute("additionalExams", additionalExams);
        model.addAttribute("additionalExamDto", additionalExamDto);
        return "medicalExam";
    }
}
