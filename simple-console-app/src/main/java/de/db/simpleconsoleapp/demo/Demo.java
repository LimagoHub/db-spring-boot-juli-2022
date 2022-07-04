package de.db.simpleconsoleapp.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.beans.beancontext.BeanContext;

@Component
//@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Demo {

    @Autowired
    //@Qualifier("lower")
    private Translator translator;

    @Value("${Demo.name}")
    private String name = "Fritz";

    public Demo() {
        System.out.println("Hallo " + name);
        System.out.println("CTOR Demo");
    }

    @PostConstruct
    private void go() {
        System.out.println("Hallo " + name);
        System.out.println(translator.translate("Hier ist go von Demo"));
    }

    @PreDestroy
    public void dispose() { // Nicht bei Prototype
        System.out.println("dispose");
    }
}
