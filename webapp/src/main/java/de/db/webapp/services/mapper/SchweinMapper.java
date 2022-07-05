package de.db.webapp.services.mapper;

import de.db.webapp.repositories.entities.SchweinEntity;
import de.db.webapp.services.model.Schwein;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinMapper {

    Schwein convert(SchweinEntity entity);
    SchweinEntity convert(Schwein schwein);

    Iterable<Schwein> convert(Iterable<SchweinEntity> entities);
}
