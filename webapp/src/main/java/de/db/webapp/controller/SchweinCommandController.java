package de.db.webapp.controller;

import de.db.webapp.controller.dto.SchweinDto;
import de.db.webapp.controller.mapper.SchweinDtoMapper;
import de.db.webapp.services.SchweineService;
import de.db.webapp.services.SchweineServiceException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/schweine")

@RequestScope
// @SessionScope bitte nicht
@AllArgsConstructor
public class SchweinCommandController {

    private final SchweineService service;
    private final SchweinDtoMapper mapper;

    @PostMapping(path="/{id}/fuettern")
    public ResponseEntity<Void> fuettern(@PathVariable  String id) throws SchweineServiceException {


        if(service.fuettern(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrUpdate(@Valid@ RequestBody SchweinDto schweinDto) throws SchweineServiceException {


        if(service.speichern(mapper.convert(schweinDto))) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> remove(@PathVariable String id) throws SchweineServiceException {


        if(service.loeschen(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> remove(@Valid@ RequestBody SchweinDto schweinDto) throws SchweineServiceException {


        if(service.loeschen(schweinDto.getId())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
