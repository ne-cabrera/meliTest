package com.example.melitest.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MutantRepository extends CrudRepository<Mutant, Integer>
{
    @Query(value = "SELECT IFNULL(counts.count_mutants, 0) as mutants, IFNULL(counts.count_humans, 0) as humans FROM " +
            "(SELECT " +
            "SUM(CASE WHEN is_mutant = 1 THEN 1 ELSE 0 END) AS count_mutants, " +
            "SUM(CASE WHEN is_mutant = 0 THEN 1 ELSE 0 END ) AS count_humans from mutantes.mutant" +
            ") as counts", nativeQuery = true)
    StatsDTO countMutants();
}

