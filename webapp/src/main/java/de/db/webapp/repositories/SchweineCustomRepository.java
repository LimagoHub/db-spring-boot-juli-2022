package de.db.webapp.repositories;

import de.db.webapp.repositories.entities.SchweinEntity;
import de.db.webapp.services.model.Schwein;

public interface SchweineCustomRepository {

    Iterable<SchweinEntity> topTenPigs();
}
