package com.tobeto.processor_service.strategy;


import org.springframework.stereotype.Component;

@Component("dataProcessing")
public class DataProcessingStrategy implements ProcessorStrategy {

    @Override
    public String process(String taskId, String processorType) {
        // Implement data processing logic here
        return "Processed data for order " + taskId+   " using " + processorType + " processor.";
    }
}
