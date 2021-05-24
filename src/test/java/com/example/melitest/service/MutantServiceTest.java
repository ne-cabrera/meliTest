package com.example.melitest.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.melitest.model.MutantRepository;
import com.example.melitest.util.MutantTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith( MockitoExtension.class )
class MutantServiceTest
{
    @Mock
    private MutantRepository mutantRepository;

    @InjectMocks
    private MutantService mutantService;

    @Test
    public void isMutantTest_isMutant()
    {
        assertTrue( mutantService.isMutant( MutantTestUtil.MUTANT_DNA ) );
    }

    @Test
    public void isMutantTest_isNotMutant()
    {
        assertFalse( mutantService.isMutant( MutantTestUtil.HUMAN_DNA ) );
    }
}