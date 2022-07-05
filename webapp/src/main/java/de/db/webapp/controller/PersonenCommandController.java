package de.db.webapp.controller;

import de.db.webapp.controller.dto.PersonDto;
import de.db.webapp.controller.mapper.PersonDtoMapper;
import de.db.webapp.services.PersonenService;
import de.db.webapp.services.PersonenServiceException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/personen")

@RequestScope
// @SessionScope bitte nicht
@AllArgsConstructor
public class PersonenCommandController {

    private final PersonenService service;
    private final PersonDtoMapper mapper;


    @PutMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrUpdate( @Valid @RequestBody final PersonDto person) throws PersonenServiceException {
        if(service.speichern(mapper.convert(person)))
            return ResponseEntity.ok().build();
        return  ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @DeleteMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@RequestBody final PersonDto person)   throws PersonenServiceException{
        return delete(person.getId());
    }
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> delete(@PathVariable final String id)  throws PersonenServiceException{
       if(service.loeschen(id))
           return ResponseEntity.ok().build();
        return  ResponseEntity.notFound().build();
    }

    /*
    @PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrUpdateNichtIdempotent(@RequestBody final PersonDto person,final UriComponentsBuilder builder) {
        person.setId(UUID.randomUUID().toString());
        final UriComponents uriComponents = builder.path("/personen/{id}").buildAndExpand(person.getId());

        return ResponseEntity.created(uriComponents.toUri()).build();
    }
    */

}
