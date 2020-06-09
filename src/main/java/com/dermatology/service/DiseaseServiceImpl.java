package com.dermatology.service;

import com.dermatology.model.Disease;
import com.dermatology.repository.DiseaseRepository;
import com.dermatology.service.interfaces.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseServiceImpl implements DiseaseService {
    @Autowired
    private DiseaseRepository diseaseRepository;

    @Override
    public void save(Disease d) {
        this.diseaseRepository.save(d);
    }

    @Override
    public Disease find(Long id) {
        return this.diseaseRepository.findById(id).orElse(null);
    }

    @Override
    public Disease findByName(String name) {
        return this.diseaseRepository.findByName(name);
    }

    @Override
    public List<String> findDistinct() {
        return this.diseaseRepository.findDistinct();
    }
}
