package com.unillanos.scientificcalculations.application.service;

import java.util.List;

public interface MatrixMultiplicationService {
    List<List<Double>> multiply(List<List<Double>> a, List<List<Double>> b, int threads);
}
