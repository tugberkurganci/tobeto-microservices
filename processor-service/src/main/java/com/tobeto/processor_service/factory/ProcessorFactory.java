package com.tobeto.processor_service.factory;

import com.tobeto.processor_service.dto.ProcessorRequest;

public interface ProcessorFactory {
    String executeProcessing(ProcessorRequest processingRequest);
}