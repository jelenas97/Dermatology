package com.dermatology.service.interfaces;

import com.dermatology.model.Disease;

public interface DiseaseService {
    void add(Disease d);

    Disease find(Long id);

    Disease findByName(String name);
}
