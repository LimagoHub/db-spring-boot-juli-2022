package de.db.webapp.services.mapper;

import de.db.webapp.repositories.entities.PersonEntity;
import de.db.webapp.services.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person convert(PersonEntity entity);
    PersonEntity convert(Person person);

    Iterable<Person> convert(Iterable<PersonEntity> entities);
}
