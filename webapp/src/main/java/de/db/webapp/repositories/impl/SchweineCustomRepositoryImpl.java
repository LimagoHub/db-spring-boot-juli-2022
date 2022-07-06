package de.db.webapp.repositories.impl;

import de.db.webapp.repositories.SchweineCustomRepository;
import de.db.webapp.repositories.entities.SchweinEntity;
import de.db.webapp.services.model.Schwein;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SchweineCustomRepositoryImpl implements SchweineCustomRepository {

    private EntityManager em;

    @Override
    public List<SchweinEntity> topTenPigs() {
        TypedQuery<SchweinEntity> query = em.createQuery("from SchweinEntity", SchweinEntity.class);
        query.setMaxResults(10);
        List<SchweinEntity> result = query.getResultList();
        return result;
    }
}
