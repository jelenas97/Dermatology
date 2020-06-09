package com.dermatology.service.interfaces;

import com.dermatology.model.Medication;

import java.util.List;

public interface MedicationService {
    void save(Medication medication);

    Medication find(Long id);

    Medication findByName(String name);

    List<String> findDistinct();
    List<Medication> getAll();

    Medication getById(long id);

}
