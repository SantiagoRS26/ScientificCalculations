package com.unillanos.scientificcalculations.domain.dto;

import java.util.List;

public record MatrixMultiplicationRequest(List<List<Double>> a, List<List<Double>> b, int threads) {}
