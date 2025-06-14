package com.unillanos.scientificcalculations.service;

import com.unillanos.scientificcalculations.application.service.MonteCarloPiService;
import com.unillanos.scientificcalculations.application.service.MonteCarloPiServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MonteCarloPiServiceTests {

    private final MonteCarloPiService service = new MonteCarloPiServiceImpl();

    @Test
    void calculatePiApproximation() {
        double pi = service.calculatePi(10000, 4);
        assertTrue(pi > 3.0 && pi < 3.3);
    }
}
