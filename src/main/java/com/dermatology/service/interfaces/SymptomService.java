package com.dermatology.service.interfaces;

import com.dermatology.model.Patient;
import com.dermatology.model.Symptom;

import java.util.List;

public interface SymptomService {
    void save(Symptom exam);

    Symptom find(Long id);

    Symptom findByName(String name);

    List<String> findDistinct();
    List<Symptom> getAll();

    Symptom getById(long id);

}
