package com.tobeto_case.task_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessTaskEvent {


   private String taskId;
   private String processorType;

}
