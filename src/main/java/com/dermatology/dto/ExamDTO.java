package com.dermatology.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ExamDTO {

    private Long patientId;
    private Long diseaseId;
    private List<Long> symptomList;
    private List<Long> medications;
    private Long additionalExam;

}
