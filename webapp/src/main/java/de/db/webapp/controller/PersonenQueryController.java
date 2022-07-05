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
public class PersonenQueryController {




    @ApiResponse(responseCode = "200", description = "Person wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    @ApiResponse(responseCode = "400", description = "Falsches Format")
    @ApiResponse(responseCode = "500", description = "interner Serverfehler")
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDto> getPersonById(@PathVariable  String id) {

        if("1234".equals(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(PersonDto.builder().id(id).vorname("John").nachname("Doe").build());
    }
    @GetMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<PersonDto>> getPersonById(
            @RequestParam (required = false,defaultValue = "Fritz") String vorname,
            @RequestParam (required = false,defaultValue = "Schmitt")String nachname) {

        System.out.println(vorname + " " + nachname);
        List<PersonDto> liste = List.of(
                PersonDto.builder().id(UUID.randomUUID().toString()).vorname("John").nachname("Doe").build(),
                PersonDto.builder().id(UUID.randomUUID().toString()).vorname("John").nachname("Wayne").build(),
                PersonDto.builder().id(UUID.randomUUID().toString()).vorname("John").nachname("Wick").build(),
                PersonDto.builder().id(UUID.randomUUID().toString()).vorname("John").nachname("Rambo").build(),
                PersonDto.builder().id(UUID.randomUUID().toString()).vorname("John").nachname("McClain").build());

        return ResponseEntity.ok(liste);
    }

    // Ersatz-Get wegen parameterobjekt (safe und idempotent)
    @PostMapping(path="/methods/to-upper", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)// Ersatzget
    public ResponseEntity<PersonDto> toUpper(@RequestBody final PersonDto person) { // Parameterobjekte
        person.setVorname(person.getVorname().toUpperCase());
        person.setNachname(person.getNachname().toUpperCase());
        return ResponseEntity.ok(person);
    }


}
