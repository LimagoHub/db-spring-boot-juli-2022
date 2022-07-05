package de.db.webapp.services.impl;

import de.db.webapp.repositories.PersonenRepository;
import de.db.webapp.repositories.entities.PersonEntity;
import de.db.webapp.services.PersonenServiceException;
import de.db.webapp.services.mapper.PersonMapper;
import de.db.webapp.services.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonenServiceImplTest {

    @InjectMocks
    private PersonenServiceImpl objectUnderTest;

    @Mock
    private PersonenRepository repoMock;

    @Mock
    private PersonMapper mapperMock;

    @Mock
    private List<String> blackListMock;

    @Test
    void speichern_nullParameter_throwsPersonenServiceException() {
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(null));
        assertEquals("Person darf nicht null sein.", ex.getMessage());
    }

    @Test
    void speichern_VornameNull_throwsPersonenServiceException() {
        final Person person = Person.builder().id(UUID.randomUUID().toString()).nachname("Doe").build();
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(person));
        assertEquals("Vorname zu kurz.", ex.getMessage());
    }
    @Test
    void speichern_VornameZuKurz_throwsPersonenServiceException() {
        final Person person = Person.builder().id(UUID.randomUUID().toString()).vorname("J").nachname("Doe").build();
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(person));
        assertEquals("Vorname zu kurz.", ex.getMessage());
    }
    @Test
    void speichern_NachNull_throwsPersonenServiceException() {
        final Person person = Person.builder().id(UUID.randomUUID().toString()).vorname("John").build();
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(person));
        assertEquals("Nachname zu kurz.", ex.getMessage());
    }
    @Test
    void speichern_nachnameZuKurz_throwsPersonenServiceException() {
        final Person person = Person.builder().id(UUID.randomUUID().toString()).vorname("John").nachname("D").build();
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(person));
        assertEquals("Nachname zu kurz.", ex.getMessage());
    }

    @Test
    void speichern_Antipath_throwsPersonenServiceException() {
        final Person person = Person.builder().id(UUID.randomUUID().toString()).vorname("John").nachname("Doe").build();

        when(blackListMock.contains(anyString())).thenReturn(true);

        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(person));
        assertEquals("Antipath", ex.getMessage());
    }

    @Test
    void speichern_UnxpectedExceptionInUnderlyingServe_throwsPersonenServiceException() {
        final Person person = Person.builder().id(UUID.randomUUID().toString()).vorname("John").nachname("Doe").build();

        when(blackListMock.contains(anyString())).thenReturn(false);
        when(repoMock.save(any())).thenThrow(ArrayIndexOutOfBoundsException.class);

        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(person));
        assertEquals("Upps", ex.getMessage());
    }

    @Test
    void speichern_HappyDay_throwsPersonenServiceException() throws Exception{

        final Person person = Person.builder().id("123456789012345678901234567890123456").vorname("John").nachname("Doe").build();

       // final PersonEntity personEntity = PersonEntity.builder().id(UUID.randomUUID().toString()).vorname("John").nachname("Doe").build();
        when(blackListMock.contains(anyString())).thenReturn(false);

         objectUnderTest.speichern(person);

         verify(mapperMock).convert(person);

    }
}