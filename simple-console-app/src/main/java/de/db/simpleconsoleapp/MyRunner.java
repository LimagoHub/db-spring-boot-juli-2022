package de.db.simpleconsoleapp;

import de.db.simpleconsoleapp.demo.Demo;
import de.db.simpleconsoleapp.math.Calculator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MyRunner implements CommandLineRunner {  // myRunner wird im Springcontext gespeichert


    private final Calculator calculator;



    @Override
    public void run(String... args) throws Exception {
        System.out.println(calculator.add(3,4));
    }
}
