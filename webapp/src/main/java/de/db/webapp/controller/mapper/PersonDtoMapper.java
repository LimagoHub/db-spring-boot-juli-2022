package de.db.webapp.controller.mapper;


import de.db.webapp.controller.dto.PersonDto;
import de.db.webapp.repositories.entities.PersonEntity;
import de.db.webapp.services.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonDtoMapper {
    Person convert(PersonDto dto);
    PersonDto convert(Person person);

    Iterable<PersonDto> convert(Iterable<Person> personen);

}
