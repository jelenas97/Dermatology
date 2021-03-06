package com.dermatology.service.interfaces;

import com.dermatology.model.Disease;

import java.util.List;

public interface DiseaseService {
    void save(Disease d);

    Disease find(Long id);

    Disease findByName(String name);

    List<String> findDistinct();

    List<Disease> getAll();

    Disease getById(long id);
}
