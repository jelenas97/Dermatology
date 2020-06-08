package com.dermatology.service.interfaces;

import com.dermatology.model.Patient;

public interface PatientService {
    void save(Patient patient);

    Patient find(Long id);

}
