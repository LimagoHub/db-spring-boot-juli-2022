package de.db.webapp.repositories;

import de.db.webapp.repositories.entities.PersonEntity;
import de.db.webapp.repositories.entities.TinyPerson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonenRepository extends CrudRepository<PersonEntity, String> {


    Iterable<PersonEntity> findByVorname(String vorname);

    @Query("select p from PersonEntity p where p.nachname like :nachname")
    Iterable<PersonEntity> xyz(String nachname);
    @Query("select p.vorname, p.nachname from PersonEntity p ")
    Iterable<Object[]> abc();

    @Query("select new de.db.webapp.repositories.entities.TinyPerson(p.id, p.nachname) from PersonEntity  p")
    Iterable<TinyPerson> findAllTinyPersones();

    List<PersonEntity> findAllAsList();

    // Patch
    @Query("update PersonEntity p set p.vorname = :vorname where p.id = :id")
    void updateVorname(String id, String vorname);
}
