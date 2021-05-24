package com.example.melitest.service;

import com.example.melitest.model.Mutant;
import com.example.melitest.model.MutantRepository;
import com.example.melitest.model.StatsDTO;
import com.example.melitest.rest.TO.Stats;
import org.springframework.stereotype.Service;

@Service
public class MutantService
{
    private final MutantRepository mutantRepository;

    public MutantService( MutantRepository mutantRepository )
    {
        this.mutantRepository = mutantRepository;
    }

    public boolean isMutant( String[] DNA )
    {
        boolean isMutant = false;

        int size = DNA.length;
        int mutantSequences = 0;
        for( int i = 0; i < size && !isMutant; i++ )
        {
            for( int j = 0; j < size && !isMutant; j ++)
            {
                if( j + 3 < size )
                {
                    if( DNA[i].charAt( j ) == DNA[i].charAt( j + 1 ) && DNA[i].charAt( j ) == DNA[i].charAt( j + 2 )
                            && DNA[i].charAt( j ) == DNA[i].charAt( j + 3 ) )
                    {
                        mutantSequences ++;
                    }
                }
                if( i + 3 < size )
                {
                    if( DNA[i].charAt( j ) == DNA[i + 1].charAt( j ) && DNA[i].charAt( j ) == DNA[i + 2].charAt( j )
                            && DNA[i].charAt( j ) == DNA[i + 3].charAt( j ) )
                    {
                        mutantSequences ++;
                    }
                }
                if( i + 3 < size && j + 3 < size )
                {
                    if( DNA[i].charAt( j ) == DNA[i + 1].charAt( j + 1 ) && DNA[i].charAt( j ) == DNA[i + 2].charAt( j + 2 )
                            && DNA[i].charAt( j ) == DNA[i + 3].charAt( j + 3 ) )
                    {
                        mutantSequences ++;
                    }
                }
                if( i - 3 >= 0 && j - 3 >= 0 )
                {
                    if( DNA[i].charAt( j ) == DNA[i - 1].charAt( j - 1 ) && DNA[i].charAt( j ) == DNA[i - 2].charAt( j - 2 )
                            && DNA[i].charAt( j ) == DNA[i - 3].charAt( j - 3 ) )
                    {
                        mutantSequences ++;
                    }
                }

                if( mutantSequences >= 2 )
                {
                    isMutant = true;
                }
            }
        }

        saveResult( isMutant );
        return isMutant;
    }

    public Stats getStats()
    {
        StatsDTO dto = mutantRepository.countMutants();

        int mutants = dto.getMutants();
        int humans = dto.getHumans();
        double ratio = mutants == 0 && humans == 0 ? 0 : (double) mutants/humans;

        return new Stats( mutants, humans, ratio );
    }

    public void saveResult( boolean isMutant )
    {
        Mutant mutant = new Mutant();
        mutant.setMutant( isMutant );

        mutantRepository.save( mutant );
    }
}
