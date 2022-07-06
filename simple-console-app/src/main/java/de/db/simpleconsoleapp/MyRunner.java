package de.db.simpleconsoleapp;

import de.db.simpleconsoleapp.common.LoggerProxy;
import de.db.simpleconsoleapp.demo.Demo;
import de.db.simpleconsoleapp.math.Calculator;
import de.db.simpleconsoleapp.math.impl.CalculatorImpl;
import de.db.simpleconsoleapp.math.impl.CalculatorSecure;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
//@AllArgsConstructor
public class MyRunner implements CommandLineRunner {  // myRunner wird im Springcontext gespeichert


    private  Calculator calculator;



    @Override
    public void run(String... args) throws Exception {

        calculator = new CalculatorImpl();
        calculator = (Calculator) LoggerProxy.newInstance(calculator);
        calculator = new CalculatorSecure(calculator);

        System.out.println(calculator.add(3,4));
    }
}
