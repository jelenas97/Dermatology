package com.dermatology.service.interfaces;

import com.dermatology.model.Patient;

import java.util.List;

public interface PatientService {
    void save(Patient patient);

    Patient find(Long id);


    List<Patient> getAll();
}
