package com.dermatology.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ExamDTO {

    private String disease;
    private Double probability;
    private List<String> symptomList;
    private List<String> medications;
    private List<String> additionalExam;

}
