package com.dermatology.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiseaseDto {

    private Long patientId;
    private String symptom;
    private String additionalExam;

}
