package com.unillanos.scientificcalculations.application.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class MatrixMultiplicationServiceImpl implements MatrixMultiplicationService {

    @Override
    public List<List<Double>> multiply(List<List<Double>> a, List<List<Double>> b, int threads) {
        int rowsA = a.size();
        int colsA = a.get(0).size();
        int colsB = b.get(0).size();

        List<List<Double>> result = new ArrayList<>(rowsA);
        for (int i = 0; i < rowsA; i++) {
            List<Double> row = new ArrayList<>(colsB);
            for (int j = 0; j < colsB; j++) {
                row.add(0.0);
            }
            result.add(row);
        }

        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<Void>> futures = new ArrayList<>();

        for (int i = 0; i < rowsA; i++) {
            final int row = i;
            futures.add(executor.submit(new Callable<>() {
                @Override
                public Void call() {
                    for (int j = 0; j < colsB; j++) {
                        double sum = 0.0;
                        for (int k = 0; k < colsA; k++) {
                            sum += a.get(row).get(k) * b.get(k).get(j);
                        }
                        result.get(row).set(j, sum);
                    }
                    return null;
                }
            }));
        }

        for (Future<Void> f : futures) {
            try {
                f.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();
        return result;
    }
}
