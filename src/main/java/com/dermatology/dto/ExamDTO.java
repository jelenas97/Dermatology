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
    private String disease;
    private List<String> symptomList;
    private List<String> medications;
    private List<String> additionalExam;

}
