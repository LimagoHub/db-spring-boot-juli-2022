package de.db.webapp.services;


import de.db.webapp.repositories.PersonenRepository;
import de.db.webapp.repositories.entities.PersonEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class SimpleService {

    private final PersonenRepository repo;

    @PostConstruct
    public void go() {
        PersonEntity p =PersonEntity.builder().id(UUID.randomUUID().toString()).vorname("John").nachname("Doe").build();
        p = repo.save(p);

        p.setVorname("Jane");
    }

    public void run() {
       repo.findById("e1dc98cc-bc85-490e-bf11-83e7d435aaa3").get().setVorname("Franz");


    }
}
