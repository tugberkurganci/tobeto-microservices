package com.tobeto.processor_service.event;

import com.tobeto.processor_service.dto.ProcessorRequest;
import com.tobeto.processor_service.factory.ProcessorFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventService {

    ProcessorFactory processorFactory;

    public EventService(ProcessorFactory processorFactory) {
        this.processorFactory = processorFactory;
    }

    @KafkaListener(topics = "processTopic")
    public void handleProcessEvent(ProcessTaskEvent event) {
    processorFactory.executeProcessing(new ProcessorRequest(event.getTaskId(), event.getProcessorType()));

    }
}
