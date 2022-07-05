package de.db.webapp.controller;

import de.db.webapp.controller.dto.PersonDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/personen")
public class PersonenController {



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
