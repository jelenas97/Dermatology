package com.dermatology.service;

import com.dermatology.model.Patient;
import com.dermatology.repository.PatientRepository;
import com.dermatology.service.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void save(Patient patient) {
        this.patientRepository.save(patient);
    }

    @Override
    public Patient find(Long id) {
        return this.patientRepository.findById(id).orElse(null);
    }
}
