package de.db.webapp.services.impl;

import de.db.webapp.repositories.SchweineRepository;
import de.db.webapp.services.SchweineService;
import de.db.webapp.services.SchweineServiceException;
import de.db.webapp.services.mapper.SchweinMapper;
import de.db.webapp.services.model.Schwein;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SchweineServiceException.class, isolation = Isolation.READ_COMMITTED)
@RequiredArgsConstructor
public class SchweineServiceImpl implements SchweineService {

    private final SchweineRepository repository;
    private final SchweinMapper mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean speichern(Schwein schwein) throws SchweineServiceException {
        try {
            boolean result = repository.existsById(schwein.getId());
            repository.save(mapper.convert(schwein));
            return result;
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Upps", e);
        }
    }


    public void bulkinsert(Iterable<Schwein> schweine) throws SchweineServiceException{
        for (Schwein s: schweine) {
            speichern(s);
        }
    }

    @Override
    public boolean loeschen(String id) throws SchweineServiceException {
        try {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
        catch (RuntimeException e) {
            throw new SchweineServiceException("Upps", e);
        }
    }
    @Transactional( isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public Optional<Schwein> findeNachId(String id) throws SchweineServiceException {
        try {
            return repository.findById(id).map(mapper::convert);
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Upps", e);
        }
    }

    @Override
    public Iterable<Schwein> findeAlle() throws SchweineServiceException {
        try {
            return mapper.convert(repository.findAll());
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Upps", e);
        }
    }

    @Override
    public boolean fuettern(String id) throws SchweineServiceException {

        try {
            Optional<Schwein> optionalSchwein = findeNachId(id);
            if(optionalSchwein.isPresent()) {
                Schwein schwein = optionalSchwein.get();
                schwein.fuettern();
                speichern(schwein);
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Upps", e);
        }
    }
}
