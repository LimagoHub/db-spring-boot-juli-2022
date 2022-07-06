package de.db.webapp.controller;

import de.db.webapp.controller.dto.PersonDto;
import de.db.webapp.controller.dto.SchweinDto;
import de.db.webapp.controller.mapper.SchweinDtoMapper;
import de.db.webapp.services.PersonenServiceException;
import de.db.webapp.services.SchweineService;
import de.db.webapp.services.SchweineServiceException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("/v1/schweine")

@RequestScope
// @SessionScope bitte nicht
@AllArgsConstructor
public class SchweinQueryController {


    private final SchweineService service;
    private final SchweinDtoMapper mapper;


    @ApiResponse(responseCode = "200", description = "Schwein wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Schwein wurde nicht gefunden")
    @ApiResponse(responseCode = "400", description = "Falsches Format")
    @ApiResponse(responseCode = "500", description = "interner Serverfehler")
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<SchweinDto> getSchweinById(@PathVariable String id) throws SchweineServiceException {
        return ResponseEntity.of(service.findeNachId(id).map(mapper::convert));
    }
    @GetMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Iterable<SchweinDto>> getAll(
            @RequestParam(required = false,defaultValue = "Fritz") String vorname,
            @RequestParam (required = false,defaultValue = "Schmitt")String nachname) throws SchweineServiceException {



        return ResponseEntity.ok(mapper.convert(service.findeAlle() ));
    }


}
