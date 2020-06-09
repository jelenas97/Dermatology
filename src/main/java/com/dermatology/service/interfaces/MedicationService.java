package com.dermatology.service.interfaces;

import com.dermatology.model.Medication;

import java.util.List;

public interface MedicationService {
    List<Medication> getAll();

    Medication getById(long id);

    void save(Medication medication);

}
