package com.dermatology.repository;

import com.dermatology.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    Medication findByName(String name);

    @Query(value = "select DISTINCT m.name from Medication m ")
    List<String> findDistinct();

}
