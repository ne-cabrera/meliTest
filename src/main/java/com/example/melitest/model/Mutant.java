package com.example.melitest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mutant
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Boolean isMutant;

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public Boolean getMutant()
    {
        return isMutant;
    }

    public void setMutant( Boolean mutant )
    {
        isMutant = mutant;
    }
}
