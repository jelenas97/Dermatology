package com.dermatology.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Patient patient;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Disease disease;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Symptom> symptomList;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Medication> medications;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<AdditionalExam> additionalExam;

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", patient=" + patient +
                ", disease=" + disease +
                ", symptomList=" + symptomList +
                ", medications=" + medications +
                ", additionalExam=" + additionalExam +
                '}';
    }
}