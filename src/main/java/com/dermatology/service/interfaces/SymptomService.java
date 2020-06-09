package com.dermatology.service.interfaces;

import com.dermatology.model.Patient;
import com.dermatology.model.Symptom;

import java.util.List;

public interface SymptomService {
    List<Symptom> getAll();

    Symptom getById(long id);

    void save(Symptom symptom);
}
