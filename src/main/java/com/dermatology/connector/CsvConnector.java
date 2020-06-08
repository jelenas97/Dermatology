package com.dermatology.connector;

import com.dermatology.model.*;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CsvConnector implements Connector {

    private List<Exam> examCases = new ArrayList<>();

    public CsvConnector(List<Exam> examCases) {
        this.examCases = examCases;
    }

    @Override
    public Collection<CBRCase> retrieveAllCases() {
        LinkedList<CBRCase> cases = new LinkedList<CBRCase>();

        try {

            for (Exam examCase : this.examCases) {
                CBRCase cbrCase = new CBRCase();
                PatientDescription patientDescription = new PatientDescription();

                patientDescription.setAge(examCase.getPatient().getAge());
                patientDescription.setGender(examCase.getPatient().getGender());

                List<String> symptoms = examCase.getSymptomList().stream()
                        .map(Symptom::getName)
                        .collect(Collectors.toList());
                patientDescription.setSymptom(symptoms);
                patientDescription.setDisease(examCase.getDisease().getName());
                List<String> medications = examCase.getMedications().stream()
                        .map(Medication::getName)
                        .collect(Collectors.toList());
                patientDescription.setMedication(medications);
                List<String> additionalExams = examCase.getAdditionalExam().stream()
                        .map(AdditionalExam::getName)
                        .collect(Collectors.toList());
                patientDescription.setAdditionalExams(additionalExams);
                patientDescription.setCaseId(examCase.getId());
                cbrCase.setDescription(patientDescription);
                cases.add(cbrCase);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cases;
    }

    @Override
    public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter arg0) {
        return null;
    }

    @Override
    public void storeCases(Collection<CBRCase> arg0) {
    }

    @Override
    public void close() {
    }

    @Override
    public void deleteCases(Collection<CBRCase> arg0) {
    }

    @Override
    public void initFromXMLfile(URL arg0) throws InitializingException {
    }

}
