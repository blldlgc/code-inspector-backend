package com.codeinspector.backend.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeinspector.backend.dto.CodeComparisonRequest;
import com.codeinspector.backend.dto.CodeComparisonResponse;
import com.codeinspector.backend.dto.CodeMetricsRequest;
import com.codeinspector.backend.dto.CodeMetricsResponse;
import com.codeinspector.backend.dto.CodeGraphRequest;
import com.codeinspector.backend.dto.GraphResponse;
import com.codeinspector.backend.service.CodeComparisonService;
import com.codeinspector.backend.service.CodeGraphService;

@RestController
@RequestMapping("/api/code")
@CrossOrigin(origins = "https://codeinspector.vercel.app")
public class CodeComparisonController {

    private final CodeComparisonService codeComparisonService;
    private final CodeGraphService codeGraphService;

    @Autowired
    public CodeComparisonController(
            CodeComparisonService codeComparisonService,

            CodeGraphService codeGraphService) {
        this.codeComparisonService = codeComparisonService;
        this.codeGraphService = codeGraphService;
    }

    @PostMapping("/compare")
    public CodeComparisonResponse compareCode(@RequestBody CodeComparisonRequest request) {
        return codeComparisonService.compareCode(request.code1(), request.code2());
    }

    @PostMapping("/metrics")
    public CodeMetricsResponse analyzeMetrics(@RequestBody CodeMetricsRequest request) {
        return codeComparisonService.analyzeMetrics(request.code());
    }


    @PostMapping("/graph")
    public GraphResponse generateGraph(@RequestBody CodeGraphRequest request) {
        return codeGraphService.analyzeCode(request.code());
    }
}