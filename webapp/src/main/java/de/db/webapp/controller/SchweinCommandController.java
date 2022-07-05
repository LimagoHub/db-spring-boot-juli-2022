package de.db.webapp.controller;

import de.db.webapp.controller.mapper.SchweinDtoMapper;
import de.db.webapp.services.SchweineService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("/v1/schweine")

@RequestScope
// @SessionScope bitte nicht
@AllArgsConstructor
public class SchweinCommandController {

    private final SchweineService service;
    private final SchweinDtoMapper mapper;

    @PostMapping(path="/{id}/fuettern")
    public ResponseEntity<Void> fuettern(@PathVariable  String id) {
        if(service.fuettern(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
