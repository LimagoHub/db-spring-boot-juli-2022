package de.db.webapp.services.configaration;


import de.db.webapp.repositories.PersonenRepository;
import de.db.webapp.services.PersonenService;
import de.db.webapp.services.impl.PersonenServiceImpl;
import de.db.webapp.services.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PersonenConfig {

    @Bean
    @Qualifier("Antipathen")
    public List<String> getBlacklist() {
        return List.of("Attila","Peter","Paul","Mary");
    }

    @Bean
    @Qualifier("fruits")
    public List<String> getFruits() {
        return List.of("Banana","Cherry","Stawberry","Apple");
    }

    @Bean
    public PersonenService getPersonenService(final PersonenRepository repo, final PersonMapper mapper, @Qualifier("Antipathen") final List<String> blacklist) {
        return new PersonenServiceImpl(repo,mapper,blacklist);
    }
}
