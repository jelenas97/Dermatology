package com.dermatology.repository;

import com.dermatology.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {

    Disease findByName(String name);

    @Query(value = "select DISTINCT m.name from Disease m ")
    List<String> findDistinct();

}
