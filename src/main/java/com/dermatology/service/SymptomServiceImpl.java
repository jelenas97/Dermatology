package com.dermatology.service;


import com.dermatology.model.Symptom;
import com.dermatology.model.Symptom;
import com.dermatology.repository.PatientRepository;
import com.dermatology.repository.SymptomRepository;
import com.dermatology.service.interfaces.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymptomServiceImpl implements SymptomService {

    @Autowired
    private SymptomRepository symptomRepository;

    @Override
    public void save(Symptom symptom) {
        this.symptomRepository.save(symptom);
    }

    @Override
    public Symptom find(Long id) {
        return this.symptomRepository.findById(id).orElse(null);
    }



    @Override
    public List<Symptom> getAll() {
        return this.symptomRepository.findAll();
    }

    @Override
    public Symptom getById(long id) {
        return this.symptomRepository.findById(id).orElse(null);
    }

    public Symptom findByName(String name) {
        return this.symptomRepository.findByName(name);
    }

    @Override
    public List<String> findDistinct() {
        return this.symptomRepository.findDistinct();
}
