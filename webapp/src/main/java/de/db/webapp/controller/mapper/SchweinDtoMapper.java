package de.db.webapp.controller.mapper;

import de.db.webapp.controller.dto.PersonDto;
import de.db.webapp.controller.dto.SchweinDto;
import de.db.webapp.services.model.Person;
import de.db.webapp.services.model.Schwein;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinDtoMapper {
    Schwein convert(SchweinDto dto);
    SchweinDto convert(Schwein schwein);

    Iterable<SchweinDto> convert(Iterable<Schwein> schweine);

}
