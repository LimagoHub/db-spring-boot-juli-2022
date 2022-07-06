package de.db.simpleconsoleapp.math.impl;

import de.db.simpleconsoleapp.math.Calculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component
@Primary
@Slf4j
public class CalculatorSecure implements Calculator{

    private final Calculator calculator;

    public CalculatorSecure(@Qualifier("logger") Calculator calculator) {
        this.calculator = calculator;
    }

    public double add(double a, double b) {
        log.warn("Du kommst hier rein");
        return calculator.add(a, b);
    }

    public double sub(double a, double b) {
        return calculator.sub(a, b);
    }
}
