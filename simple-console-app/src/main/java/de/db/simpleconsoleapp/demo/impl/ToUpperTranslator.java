package de.db.simpleconsoleapp.demo.impl;

import de.db.simpleconsoleapp.demo.Translator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("upper")
@Profile("test")
public class ToUpperTranslator implements Translator {
    @Override
    public String translate(String message) {
        return message.toUpperCase();
    }
}
