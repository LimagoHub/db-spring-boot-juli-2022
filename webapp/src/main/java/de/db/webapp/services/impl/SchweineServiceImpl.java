package de.db.webapp.services.impl;

import de.db.webapp.repositories.SchweineRepository;
import de.db.webapp.services.SchweineService;
import de.db.webapp.services.mapper.SchweinMapper;
import de.db.webapp.services.model.Schwein;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SchweineServiceImpl implements SchweineService {

    private final SchweineRepository repository;
    private final SchweinMapper mapper;

    @Override
    public boolean speichern(Schwein schwein) {
        boolean result = repository.existsById(schwein.getId());
        repository.save(mapper.convert(schwein));
        return result;
    }

    @Override
    public boolean loeschen(String id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Schwein> findeNachId(String id) {
        return repository.findById(id).map(mapper::convert);
    }

    @Override
    public Iterable<Schwein> findeAlle() {
        return mapper.convert(repository.findAll());
    }

    @Override
    public boolean fuettern(String id) {
        Optional<Schwein> optionalSchwein = findeNachId(id);
        if(optionalSchwein.isPresent()) {
            Schwein schwein = optionalSchwein.get();
            schwein.fuettern();
            speichern(schwein);
            return true;
        }
        return false;
    }
}
