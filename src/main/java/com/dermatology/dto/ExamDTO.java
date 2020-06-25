package com.dermatology.dto;

import com.dermatology.model.AdditionalExam;
import com.dermatology.model.Exam;
import com.dermatology.model.Medication;
import com.dermatology.model.Symptom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ExamDTO {

    private String diseaseExam;
    private long patientId;
    private Double probability;
    private List<String> symptomList;
    private List<String> medications;
    private List<String> additionalExams;

    public ExamDTO(Exam exam, Double probability) {
        this.diseaseExam = exam.getDisease().getName();
        this.probability = probability;

        this.symptomList = exam.getSymptomList().stream()
                .map(Symptom::getName)
                .collect(Collectors.toList());
        this.medications = exam.getMedications().stream()
                .map(Medication::getName)
                .collect(Collectors.toList());
        this.additionalExams = exam.getAdditionalExam().stream()
                .map(AdditionalExam::getName)
                .collect(Collectors.toList());

    }

}
