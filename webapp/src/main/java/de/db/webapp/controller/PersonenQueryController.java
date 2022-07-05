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
public class PersonenQueryController {

    private final PersonenService service;
    private final PersonDtoMapper mapper;

    @ApiResponse(responseCode = "200", description = "Person wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    @ApiResponse(responseCode = "400", description = "Falsches Format")
    @ApiResponse(responseCode = "500", description = "interner Serverfehler")
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDto> getPersonById(@PathVariable  String id) throws PersonenServiceException {
        return ResponseEntity.of(service.findePersonNachID(id).map(mapper::convert));
    }
    @GetMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Iterable<PersonDto>> getPersonById(
            @RequestParam (required = false,defaultValue = "Fritz") String vorname,
            @RequestParam (required = false,defaultValue = "Schmitt")String nachname) throws PersonenServiceException {



        return ResponseEntity.ok(mapper.convert(service.findeAlle() ));
    }

    // Ersatz-Get wegen parameterobjekt (safe und idempotent)
    @PostMapping(path="/methods/to-upper", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)// Ersatzget
    public ResponseEntity<PersonDto> toUpper(@RequestBody final PersonDto person) { // Parameterobjekte
        person.setVorname(person.getVorname().toUpperCase());
        person.setNachname(person.getNachname().toUpperCase());
        return ResponseEntity.ok(person);
    }


}
