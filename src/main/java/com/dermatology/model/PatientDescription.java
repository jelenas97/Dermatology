package com.dermatology.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PatientDescription implements CaseComponent {

    private String gender;
    private int age;
    private String disease;
    private List<String> medication;
    private List<String> symptom;
    private List<String> additionalExams;
    private Long caseId;


    @Override
    public Attribute getIdAttribute() {
        return null;
    }

    @Override
    public String toString() {
        return "PatientDescription{" +
                "gender='" + gender + '\'' +
                ", age=" + age +
                ", disease='" + disease + '\'' +
                ", medication=" + medication +
                ", symptom=" + symptom +
                ", additionalExams=" + additionalExams +
                ", caseId=" + caseId +
                '}';
    }
}
