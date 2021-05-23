package com.example.melitest.rest;

import com.example.melitest.rest.TO.Person;
import com.example.melitest.rest.TO.Stats;
import com.example.melitest.service.MutantService;
import com.example.melitest.util.MutantTestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MutantControllerIntegrationTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MutantService mutantService;

    @Test
    public void statsTest_humanCount() throws Exception
    {
        Person human = new Person();
        human.setDna( MutantTestUtil.HUMAN_DNA );

        Stats oldStats = getStats();
        callMutantService( human ).andExpect( status().isForbidden() );
        Stats newStats = getStats();

        assertEquals( oldStats.getCountHumanDna() + 1, newStats.getCountHumanDna() );
        assertEquals( oldStats.getCountMutantDna(), newStats.getCountMutantDna() );
        assertEquals( (double) oldStats.getCountMutantDna()/( oldStats.getCountHumanDna() + 1), newStats.getRatio() );
    }

    @Test
    public void statsTest_MutantCount() throws Exception
    {
        Person mutant = new Person();
        mutant.setDna( MutantTestUtil.MUTANT_DNA );

        Stats oldStats = getStats();
        callMutantService( mutant ).andExpect( status().isOk() );
        Stats newStats = getStats();

        assertEquals( oldStats.getCountMutantDna() + 1, newStats.getCountMutantDna() );
        assertEquals( oldStats.getCountHumanDna(), newStats.getCountHumanDna() );
        assertEquals( (double) ( oldStats.getCountMutantDna() + 1 )/oldStats.getCountHumanDna(), newStats.getRatio() );
    }

    private ResultActions callMutantService( Person person ) throws Exception {
        return mockMvc.perform( post( "/mutant" )
                .contentType( "application/json" )
                .content( objectMapper.writeValueAsString( person ) ) );
    }

    private Stats getStats() throws Exception {
        String statsResponse = mockMvc.perform( get( "/stats" ) )
                .andExpect( status().isOk() )
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readValue( statsResponse, Stats.class );
    }
}