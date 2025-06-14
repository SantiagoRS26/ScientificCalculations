package com.unillanos.scientificcalculations.presentation.controller;

import com.unillanos.scientificcalculations.application.service.MatrixMultiplicationService;
import com.unillanos.scientificcalculations.application.service.MonteCarloPiService;
import com.unillanos.scientificcalculations.domain.dto.MatrixMultiplicationRequest;
import com.unillanos.scientificcalculations.domain.dto.MatrixMultiplicationResponse;
import com.unillanos.scientificcalculations.domain.dto.MonteCarloPiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/algorithms")
public class AlgorithmsController {

    private final MatrixMultiplicationService matrixService;
    private final MonteCarloPiService piService;

    public AlgorithmsController(MatrixMultiplicationService matrixService, MonteCarloPiService piService) {
        this.matrixService = matrixService;
        this.piService = piService;
    }

    @PostMapping("/matrix")
    public ResponseEntity<MatrixMultiplicationResponse> multiply(@RequestBody MatrixMultiplicationRequest request) {
        List<List<Double>> result = matrixService.multiply(request.a(), request.b(), request.threads());
        return ResponseEntity.ok(new MatrixMultiplicationResponse(result));
    }

    @GetMapping("/pi")
    public ResponseEntity<MonteCarloPiResponse> calculatePi(@RequestParam long iterations, @RequestParam int threads) {
        double pi = piService.calculatePi(iterations, threads);
        return ResponseEntity.ok(new MonteCarloPiResponse(pi));
    }
}
