package com.example.melitest.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MutantRepository extends CrudRepository<Mutant, Integer>
{
    @Query(value = "SELECT SUM(CASE WHEN is_mutant = true THEN 1 ELSE 0 END) AS mutants," +
            "SUM(CASE WHEN is_mutant = 0 THEN 1 ELSE 0 END ) AS humans FROM mutant", nativeQuery = true)
    StatsDTO countMutants();
}
