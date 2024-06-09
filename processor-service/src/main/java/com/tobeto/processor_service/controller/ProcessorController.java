package com.tobeto.processor_service.controller;

import com.tobeto.processor_service.dto.ProcessorRequest;
import com.tobeto.processor_service.factory.ProcessorFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/processing")
public class ProcessorController {

    private final ProcessorFactory processorFactory;

    public ProcessorController(ProcessorFactory processorFactory) {
        this.processorFactory = processorFactory;
    }

    @PostMapping
    public ResponseEntity<String> process(@RequestBody ProcessorRequest processingRequest) {
        return ResponseEntity.ok(processorFactory.executeProcessing(processingRequest));
    }
}
