package com.example.melitest.rest.TO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats
{
    private int countMutantDna;
    private int countHumanDna;
    private double ratio;

    public Stats( int countMutantDna, int countHumanDna, double ratio )
    {
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;
        this.ratio = ratio;
    }

    @JsonProperty( "count_human_dna" )
    public int getCountHumanDna()
    {
        return countHumanDna;
    }

    public void setCountHumanDna( int countHumanDna )
    {
        this.countHumanDna = countHumanDna;
    }

    @JsonProperty( "count_mutant_dna" )
    public int getCountMutantDna()
    {
        return countMutantDna;
    }

    public void setCountMutantDna( int countMutantDna )
    {
        this.countMutantDna = countMutantDna;
    }

    public double getRatio()
    {
        return ratio;
    }

    public void setRatio( double ratio )
    {
        this.ratio = ratio;
    }
}
