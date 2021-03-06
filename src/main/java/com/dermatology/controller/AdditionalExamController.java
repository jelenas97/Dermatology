package com.dermatology.controller;

import com.dermatology.cbr.CbrApplication;
import com.dermatology.dto.AdditionalExamDto;
import com.dermatology.dto.DiseaseDto;
import com.dermatology.dto.ExamDTO;
import com.dermatology.dto.MedicamentDto;
import com.dermatology.model.AdditionalExam;
import com.dermatology.model.Exam;
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

import javax.swing.tree.ExpandVetoException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.reverse;

@Controller
@RequestMapping(value = "additionalExam")
public class AdditionalExamController {

    @Autowired
    private AdditionalExamService additionalExamService;

    @Autowired
    private ExamService examService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private SymptomService symptomService;

    @Autowired
    private DiseaseService diseaseService;


    @PostMapping("/predict/{patientId}")
    public String newCase(@ModelAttribute("additionalExamDto") AdditionalExamDto additionalExamDto, @PathVariable String patientId, Model model) {

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
            List<ExamDTO> foundCasesDTO = new ArrayList<>();
            List<String> uniqueExams = new ArrayList<>();
            DecimalFormat df = new DecimalFormat("####0.000");
            int i = 1;
            for (RetrievalResult res : cases) {
                String[] s = res.get_case().getDescription().toString().split("caseId=");
                Long id = Long.parseLong(s[1].split("}")[0]);
                Exam e2 = this.examService.find(id);
                ExamDTO dto = new ExamDTO(e2, res.getEval() * 100);

                if (!uniqueExams.contains(e2.getAdditionalExam().get(0).getName()) && uniqueExams.size() < 5) {
                    uniqueExams.add(e2.getAdditionalExam().get(0).getName());
                    foundCasesDTO.add(new ExamDTO(e2, Double.parseDouble(df.format(res.getEval() * 100))));
                }

                model.addAttribute("foundCases", foundCasesDTO);

            }
            int i2 = 1;
            for (ExamDTO e : foundCasesDTO) {
                System.out.println(i2 + " . pronadjeni slucaj je : " + e.toString());
                i2++;
            }
        }catch(Exception e){
            return null;
        }

        //rbr

        JIPEngine engine = new JIPEngine();

        engine.consultFile("src/main/java/com/dermatology/data/program.pl");

        List<String> symptomsFront = Arrays.stream(additionalExamDto.getSymptoms().split(",")).collect(Collectors.toList());
        String symptomsProlog = additionalExamDto.getSymptoms();
        JIPQuery query;
        List<ExamDTO> foundCases2DTO = new ArrayList<>();

        if (symptomsFront.size() == 0) {
            return null;
        } else if (symptomsFront.size() == 1)
        {
            try {
                query = engine.openSynchronousQuery("ispitivanja_preko_jednog_simptoma(L7," + symptomsProlog + ")");

                JIPTerm solution;
                while ((solution = query.nextSolution()) != null) {
                    System.out.println("solution: " + solution);
                    System.out.println("unbounded: " + solution.getUnboundedVariables().toString());
                    for (JIPVariable var : solution.getVariables()) {
                        String result = var.getValue().toString();
                        result = result.replace("'", "");
                        result = result.replace(".", "");
                        result = result.replace("(", "");
                        result = result.replace(")", "");
                        result = result.replace(",,", ",");
                        result = result.replace(",[", ";");
                        result = result.replace("],", "");
                        result = result.replace("];", "");
                        result = result.replace("]", "");


                        String[] additionalExams = result.split(";");
                        System.out.println(additionalExams);
                        for (String additionalExam : additionalExams) {
                                String[] finalList = additionalExam.split(",");
                                ExamDTO examRbr = new ExamDTO();
                                examRbr.setProbability(Double.parseDouble(finalList[0]));
                                examRbr.setAdditionalExams(new ArrayList<String>());
                                examRbr.getAdditionalExams().add(finalList[1]);
                                foundCases2DTO.add(examRbr);

                        }
                    }
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        } else {
            try {
                query = engine.openSynchronousQuery("ispitivanja_preko_simptoma([" + symptomsProlog + "], L9)");

                JIPTerm solution;
                while ((solution = query.nextSolution()) != null) {
                    System.out.println("solution: " + solution);
                    System.out.println("unbounded: " + solution.getUnboundedVariables().toString());
                    for (JIPVariable var : solution.getVariables()) {
                        String result = var.getValue().toString();
                        result = result.replace("'", "");
                        result = result.replace(".", "");
                        result = result.replace("(", "");
                        result = result.replace(")", "");
                        result = result.replace(",,", ",");
                        result = result.replace(",[", ";");
                        result = result.replace("],", "");
                        result = result.replace("];", "");
                        result = result.replace("]", "");


                        String[] additionalExams = result.split(";");
                        System.out.println(additionalExams);
                        for (String additionalExam : additionalExams) {

                                String[] finalList = additionalExam.split(",");
                                ExamDTO examRbr = new ExamDTO();
                                examRbr.setProbability(Double.parseDouble(finalList[1]));
                                examRbr.setAdditionalExams(new ArrayList<String>());
                                examRbr.getAdditionalExams().add(finalList[0]);
                                foundCases2DTO.add(examRbr);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        foundCases2DTO.sort(Comparator.comparing(ExamDTO::getProbability).reversed());
        List<ExamDTO> foundCases2DTOsplit = new ArrayList<ExamDTO>();
        int brojac = 0;
        for(ExamDTO exam : foundCases2DTO) {
            if(brojac != 5) {
                foundCases2DTOsplit.add(exam);
                brojac++;
            }

        }
        model.addAttribute("foundCasesRbr", foundCases2DTOsplit);




        DiseaseDto diseaseDto = new DiseaseDto();
        MedicamentDto medicamentDto = new MedicamentDto();

        diseaseDto.setPatientId(Long.parseLong(patientId));
        medicamentDto.setPatientId(Long.parseLong(patientId));
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
        //foundCasesDTO
        return "medicalExam";
    }

    @GetMapping()
    public List<String> getAll() {

        List<String> additionExams = this.additionalExamService.findDistinct();
        return additionExams;
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

    @PostMapping("/prolog/{patientId}")
    public ModelAndView additionalExamProlog(@ModelAttribute("additionalExamDto") AdditionalExamDto additionalExamDto, @PathVariable String patientId, Model model) {
        JIPEngine engine = new JIPEngine();

        engine.consultFile("src/main/java/com/dermatology/data/program.pl");

        List<String> symptoms = Arrays.stream(additionalExamDto.getSymptoms().split(",")).collect(Collectors.toList());
        String symptomsProlog = additionalExamDto.getSymptoms();
        JIPQuery query;
        if (symptoms.size() == 0) {
            return null;
        } else if (symptoms.size() == 1)
        {

            query = engine.openSynchronousQuery("ispitivanja_preko_jednog_simptoma(L7,"+ symptomsProlog +")");

        } else
        {
            query = engine.openSynchronousQuery("ispitivanja_preko_simptoma(["+ symptomsProlog +"], L9)");

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

                String[] additionalExams = result.split(",");
                System.out.println(additionalExams);

            }
        }
        return new ModelAndView("showDiseasePrediction", model.asMap());


    }

}
