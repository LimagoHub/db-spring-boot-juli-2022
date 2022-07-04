package de.db.simpleconsoleapp.demo.impl;

import de.db.simpleconsoleapp.demo.Translator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Primary
//@Qualifier("lower")
@Profile("production")
public class ToLowerTranslator implements Translator {
    @Override
    public String translate(String message) {
        return message.toLowerCase();
    }
}
