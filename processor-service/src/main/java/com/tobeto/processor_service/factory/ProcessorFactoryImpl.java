package com.tobeto.processor_service.factory;



import com.tobeto.processor_service.dto.ProcessorRequest;
import com.tobeto.processor_service.event.ProcessTaskEvent;
import com.tobeto.processor_service.strategy.ProcessorStrategy;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProcessorFactoryImpl implements ProcessorFactory {

    private final Map<String, ProcessorStrategy> processorStrategyMap;
    private final KafkaTemplate<String, ProcessTaskEvent> kafkaTemplate;


    public ProcessorFactoryImpl(Map<String, ProcessorStrategy> processorStrategyMap,KafkaTemplate<String, ProcessTaskEvent> kafkaTemplate) {
        this.processorStrategyMap = processorStrategyMap;
        this.kafkaTemplate = kafkaTemplate;
    }

    public ProcessorStrategy getProcessorStrategy(String processorType) {
        ProcessorStrategy processorStrategy = processorStrategyMap.get(processorType);
        if (processorStrategy == null) {
            throw new IllegalArgumentException("Invalid processor type: " + processorType);
        }
        return processorStrategy;
    }

    @Override
    public String executeProcessing(ProcessorRequest processRequest) {
        ProcessorStrategy processorStrategy = getProcessorStrategy(processRequest.processorType());
       String result= processorStrategy.process(processRequest.taskId(), processRequest.processorType());
       kafkaTemplate.send("updateTask", new ProcessTaskEvent(processRequest.taskId(),"access"));
       return result;
    }
}
