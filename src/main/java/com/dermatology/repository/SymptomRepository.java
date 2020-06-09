package com.dermatology.repository;

import com.dermatology.model.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymptomRepository extends JpaRepository<Symptom, Long> {
    Symptom findByName(String name);

    @Query(value = "select DISTINCT m.name from Symptom m ")
    List<String> findDistinct();

}
