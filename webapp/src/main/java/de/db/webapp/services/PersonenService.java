package de.db.webapp.services;

import de.db.webapp.services.model.Person;

import java.util.Optional;

public interface PersonenService {

    boolean speichern(Person person) throws PersonenServiceException ;
    Optional<Person> findePersonNachID(String id) throws PersonenServiceException ;
    Iterable<Person> findeAlle() throws PersonenServiceException ;

    boolean loeschen(String id) throws PersonenServiceException ;
    default boolean loeschen(Person person) throws PersonenServiceException {
        return loeschen(person.getId());
    }



}
