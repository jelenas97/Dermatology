package com.dermatology.controller;

import com.dermatology.cbr.CbrApplication;
import com.dermatology.dto.ExamDTO;
import com.dermatology.dto.MedicamentDto;
import com.dermatology.model.Exam;
import com.dermatology.model.Patient;
import com.dermatology.model.PatientDescription;
import com.dermatology.service.interfaces.ExamService;
import com.dermatology.service.interfaces.MedicationService;
import com.dermatology.service.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping(value = "medicament")
public class MedicamentController {

    @Autowired
    private ExamService examService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicationService medicationService;

    @PostMapping("/predict/{patientId}")
    public ModelAndView predict(Model model, @Valid @ModelAttribute("medicationDto") MedicamentDto medicamentDto, @PathVariable String patientId){
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
                    foundCasesDTO.add(new ExamDTO(e2, Double.parseDouble(df.format(res.getEval()))));
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

        return new ModelAndView("showMedicationPrediction", model.asMap());
    }

    @GetMapping()
    public List<String> getAll() {

        List<String> medications = this.medicationService.findDistinct();
        return medications;
    }

}
