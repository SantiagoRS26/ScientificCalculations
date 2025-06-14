package com.unillanos.scientificcalculations.service;

import com.unillanos.scientificcalculations.application.service.MatrixMultiplicationService;
import com.unillanos.scientificcalculations.application.service.MatrixMultiplicationServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixMultiplicationServiceTests {

    private final MatrixMultiplicationService service = new MatrixMultiplicationServiceImpl();

    @Test
    void multiply2x2Matrices() {
        List<List<Double>> a = Arrays.asList(
                Arrays.asList(1.0, 2.0),
                Arrays.asList(3.0, 4.0)
        );
        List<List<Double>> b = Arrays.asList(
                Arrays.asList(5.0, 6.0),
                Arrays.asList(7.0, 8.0)
        );
        List<List<Double>> result = service.multiply(a, b, 2);
        assertEquals(19.0, result.get(0).get(0));
        assertEquals(22.0, result.get(0).get(1));
        assertEquals(43.0, result.get(1).get(0));
        assertEquals(50.0, result.get(1).get(1));
    }
}
