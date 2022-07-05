package de.db.webapp.services;

import de.db.webapp.services.model.Schwein;

import java.util.Optional;

public interface SchweineService {

    boolean speichern (Schwein schwein);
    boolean loeschen(String id);
    Optional<Schwein> findeNachId(String id);
    Iterable<Schwein> findeAlle();
    boolean fuettern(String id);
}
