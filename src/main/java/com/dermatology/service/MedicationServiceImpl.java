package com.dermatology.service;

import com.dermatology.model.Medication;
import com.dermatology.repository.ExamRepository;
import com.dermatology.repository.MedicationRepository;
import com.dermatology.service.interfaces.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public List<Medication> getAll() {
        return this.medicationRepository.findAll();
    }

    @Override
    public Medication getById(long id) {
        return this.medicationRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Medication medication) {
        this.medicationRepository.save(medication);
    }
}
