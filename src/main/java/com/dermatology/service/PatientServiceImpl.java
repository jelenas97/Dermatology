package com.dermatology.service;

import com.dermatology.model.Patient;
import com.dermatology.repository.MedicationRepository;
import com.dermatology.repository.PatientRepository;
import com.dermatology.service.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> getAll() {
        return this.patientRepository.findAll();
    }

    @Override
    public Patient getById(long id) {
        return this.patientRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Patient patient) {
        this.patientRepository.save(patient);
    }
}
