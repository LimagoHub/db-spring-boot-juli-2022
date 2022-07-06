package de.db.webapp.controller;

import de.db.webapp.controller.dto.PersonDto;
import de.db.webapp.services.PersonenService;
import de.db.webapp.services.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @Sql({"/create.sql","/insert.sql"})
@ExtendWith(SpringExtension.class)
class PersonControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PersonenService serviceMock;

    @Test
    void test1() throws Exception{

        final Optional<Person> optionalPerson = Optional.of(Person.builder().id("1").vorname("John").nachname("Doe").build());
        when(serviceMock.findePersonNachID(anyString())).thenReturn(optionalPerson);

        var dto = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);
        assertEquals("John", dto.getVorname());
    }

    @Test
    void test2() throws Exception{
        var dto = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", String.class);
        System.out.println(dto);
    }


    @Test
    void test3() throws Exception{

        final Optional<Person> optionalPerson = Optional.of(Person.builder().id("1").vorname("John").nachname("Doe").build());
        when(serviceMock.findePersonNachID(anyString())).thenReturn(optionalPerson);


        var entity = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);
        var dto = entity.getBody();
        assertEquals("John", dto.getVorname());
        assertEquals(HttpStatus.OK, entity.getStatusCode());

    }

    @Test
    void test4() throws Exception{

        final Optional<Person> optionalPerson = Optional.empty();
        when(serviceMock.findePersonNachID(anyString())).thenReturn(optionalPerson);


        var entity = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);


        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());

    }

    @Test
    void test5() throws Exception{

        final Optional<Person> optionalPerson = Optional.empty();
        when(serviceMock.findePersonNachID(anyString())).thenReturn(optionalPerson);


        var entity = restTemplate.exchange("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", HttpMethod.GET,null, PersonDto.class);


        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());

    }

    @Test
    void test6() throws Exception{

        List<Person> liste = List.of(
                Person.builder().id("1").vorname("John").nachname("Doe").build(),
                Person.builder().id("2").vorname("John").nachname("Rambo").build(),
                Person.builder().id("3").vorname("John").nachname("Wick").build()
        );


        when(serviceMock.findeAlle()).thenReturn(liste);


        var entity = restTemplate.exchange(
                "/v1/personen",
                HttpMethod.GET,null,
                new ParameterizedTypeReference<List<PersonDto>>() { });


        assertEquals(3, entity.getBody().size());

    }

    @Test
    void test7() throws Exception{


        final Person person = Person.builder().id("X".repeat(36)).vorname("John").nachname("Doe").build();
        final PersonDto toSend = PersonDto.builder().id("X".repeat(36)).vorname("John").nachname("Doe").build();

        HttpEntity<PersonDto> httpEntity = new HttpEntity<>(toSend);


        var entity = restTemplate.exchange(
                "/v1/personen",
                HttpMethod.PUT,httpEntity,
                Void.class);


        verify(serviceMock,times(1)).speichern(person);

    }
}