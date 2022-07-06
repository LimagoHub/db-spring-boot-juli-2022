package de.db.webapp.services;

import de.db.webapp.services.model.Schwein;

import java.util.Optional;

public interface SchweineService {

    boolean speichern (Schwein schwein) throws SchweineServiceException;
    boolean loeschen(String id) throws SchweineServiceException;
    Optional<Schwein> findeNachId(String id) throws SchweineServiceException;
    Iterable<Schwein> findeAlle() throws SchweineServiceException;
    boolean fuettern(String id) throws SchweineServiceException;
}
