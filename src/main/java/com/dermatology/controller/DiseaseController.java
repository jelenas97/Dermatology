package com.dermatology.controller;

import com.dermatology.cbr.CbrApplication;
import com.dermatology.dto.DiseaseDto;
import com.dermatology.dto.ExamDTO;
import com.dermatology.model.Exam;
import com.dermatology.model.Patient;
import com.dermatology.model.PatientDescription;
import com.dermatology.service.interfaces.DiseaseService;
import com.dermatology.service.interfaces.ExamService;
import com.dermatology.service.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;

import javax.validation.Valid;
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
    //List<ExamDTO>
    public String predict(Model model, @Valid @ModelAttribute("diseaseDto")DiseaseDto diseaseDto, @PathVariable String patientId){
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
            int i = 1;
            for (RetrievalResult res : cases) {
                String[] s = res.get_case().getDescription().toString().split("caseId=");
                Long id = Long.parseLong(s[1].split("}")[0]);
                Exam e2 = this.examService.find(id);
                ExamDTO dto = new ExamDTO(e2, res.getEval());
                foundCasesDTO.add(dto);
                System.out.println(i + " . pronadjeni slucaj je : " + dto);
            }
        } catch (Exception e) {
            //null
            return "bilo sta";
        }
        //foundCasesDTO
        return "hello";
    }

    @GetMapping()
    public List<String> getAll() {

        List<String> disease = this.diseaseService.findDistinct();
        return disease;
    }

}
