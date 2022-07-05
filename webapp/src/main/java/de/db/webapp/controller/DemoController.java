package de.db.webapp.controller;


import de.db.webapp.services.SimpleService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@AllArgsConstructor
public class DemoController {

    private final SimpleService service;

    @GetMapping(path="/gruss", produces = MediaType.TEXT_PLAIN_VALUE)
    public String gruss() {
        return "Hallo Demo";
    }
    @GetMapping(path="/service", produces = MediaType.TEXT_PLAIN_VALUE)
    public String run() {

        service.run();
        return "OK";
    }
}
