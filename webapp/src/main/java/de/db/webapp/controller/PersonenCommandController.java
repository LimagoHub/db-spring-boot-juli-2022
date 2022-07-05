package de.db.webapp.controller;

import de.db.webapp.controller.dto.PersonDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
public class PersonenCommandController {



    @PutMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrUpdate( @Valid @RequestBody final PersonDto person) {
        System.out.println(person);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @DeleteMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@RequestBody final PersonDto person)  {
        return delete(person.getId());
    }
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> delete(@PathVariable final String id)  {
        // löschen
        return ResponseEntity.notFound().build();
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
