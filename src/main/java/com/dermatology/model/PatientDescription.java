package com.dermatology.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PatientDescription implements CaseComponent {

    private String gender;
    private int age;
    private String disease;
    private List<String> medication;
    private List<String> symptom;
    private Long caseId;
    private List<String> additionalExams;

    @Override
    public Attribute getIdAttribute() {
        return null;
    }
}
