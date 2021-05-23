package com.example.melitest.rest;

import com.example.melitest.rest.TO.Person;
import com.example.melitest.rest.TO.Stats;
import com.example.melitest.service.MutantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( path = "" )
public class MutantController
{
    private final MutantService mutantService;

    public MutantController( MutantService mutantService )
    {
        this.mutantService = mutantService;
    }

    @PostMapping( path = "/mutant")
    public ResponseEntity<String> isMutant( @RequestBody Person person )
    {
        return mutantService.isMutant( person.getDna() ) ? new ResponseEntity<>( HttpStatus.OK )
                : new ResponseEntity<>( HttpStatus.FORBIDDEN );
    }

    @GetMapping( path = "/stats" )
    public Stats getStats()
    {
        return mutantService.getStats();
    }
}
