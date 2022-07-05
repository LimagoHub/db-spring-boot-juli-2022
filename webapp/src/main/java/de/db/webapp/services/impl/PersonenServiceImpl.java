package de.db.webapp.services.impl;

import de.db.webapp.repositories.PersonenRepository;
import de.db.webapp.services.PersonenService;
import de.db.webapp.services.PersonenServiceException;
import de.db.webapp.services.mapper.PersonMapper;
import de.db.webapp.services.model.Person;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional

public class PersonenServiceImpl implements PersonenService {

    private final PersonenRepository repo;
    private final PersonMapper mapper;

    private final List<String> blacklist;

    public PersonenServiceImpl(final PersonenRepository repo, final PersonMapper mapper, final List<String> blacklist) {
        this.repo = repo;
        this.mapper = mapper;
        this.blacklist = blacklist;
    }

    /*
            1. wenn person == null -> PSE
            2. Vorname null -> PSE
            3. Vorname zu kurz -> PSE
            4. Nachname null -> PSE
            5. Nachname zu kurz -> PSE
            6. Attila -> PSE
            7. underliying Service Exception ->PSE
            8. Happy Day person an repo übergeben

         */
    @Override
    public boolean speichern(Person person) throws PersonenServiceException {
        try {
            pruefePerson(person);
            boolean result = repo.existsById(person.getId());
            repo.save(mapper.convert(person));
            return result;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps",e);
        }
    }

    private void pruefePerson(Person person) throws PersonenServiceException {
        validate(person);
        businessCheck(person);
    }

    private void businessCheck(Person person) throws PersonenServiceException {
        if(blacklist.contains(person.getVorname()))
            throw new PersonenServiceException("Antipath");
    }

    private void validate(Person person) throws PersonenServiceException {
        if(person == null)
            throw new PersonenServiceException("Person darf nicht null sein.");

        if(person.getVorname() == null || person.getVorname().length() < 2)
            throw new PersonenServiceException("Vorname zu kurz.");

        if(person.getNachname() == null || person.getNachname().length() < 2)
            throw new PersonenServiceException("Nachname zu kurz.");
    }

    @Override
    public Optional<Person> findePersonNachID(String id) throws PersonenServiceException {
        try {
            return repo.findById(id).map(mapper::convert);
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Ein Fehler ist aufgetreten",e);
        }
    }

    @Override
    public Iterable<Person> findeAlle() throws PersonenServiceException {
        try {
            return mapper.convert(repo.findAll());
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Ein Fehler ist aufgetreten",e);
        }
    }

    @Override
    public boolean loeschen(String id) throws PersonenServiceException {
        try {
           if (repo.existsById(id)) {
               repo.deleteById(id);
               return true;
           }
           return false;

        } catch (RuntimeException e) {
            throw new PersonenServiceException("Ein Fehler ist aufgetreten",e);
        }
    }
}
