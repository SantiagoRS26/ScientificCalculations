package com.unillanos.scientificcalculations.application.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MonteCarloPiServiceImpl implements MonteCarloPiService {
    @Override
    public double calculatePi(long iterations, int threads) {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<Long>> futures = new ArrayList<>();
        long iterationsPerThread = iterations / threads;

        for (int i = 0; i < threads; i++) {
            futures.add(executor.submit(new Callable<>() {
                @Override
                public Long call() {
                    long inside = 0;
                    ThreadLocalRandom random = ThreadLocalRandom.current();
                    for (long j = 0; j < iterationsPerThread; j++) {
                        double x = random.nextDouble();
                        double y = random.nextDouble();
                        if (x * x + y * y <= 1.0) {
                            inside++;
                        }
                    }
                    return inside;
                }
            }));
        }

        long insideTotal = 0;
        for (Future<Long> f : futures) {
            try {
                insideTotal += f.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();
        return 4.0 * insideTotal / iterations;
    }
}
