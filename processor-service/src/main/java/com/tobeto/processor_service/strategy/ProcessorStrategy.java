package com.tobeto.processor_service.strategy;


public interface ProcessorStrategy {
    String process(String taskId, String processorType);
}
