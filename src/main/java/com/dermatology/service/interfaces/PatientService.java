package com.dermatology.service.interfaces;

import com.dermatology.model.Medication;
import com.dermatology.model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAll();

    Patient getById(long id);

    void save(Patient patient);
}
