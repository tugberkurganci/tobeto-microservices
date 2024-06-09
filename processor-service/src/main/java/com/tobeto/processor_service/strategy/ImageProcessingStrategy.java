package com.tobeto.processor_service.strategy;

import org.springframework.stereotype.Component;

@Component("imageProcessing")
public class ImageProcessingStrategy implements ProcessorStrategy {

    @Override
    public String process(String taskId, String processorType) {
        // Implement image processing logic here
        return "Processed image for order " + taskId + " with amount " +" using " + processorType + " processor.";
    }
}
