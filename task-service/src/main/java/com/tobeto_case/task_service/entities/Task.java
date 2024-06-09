package com.tobeto_case.task_service.entities;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "tasks")
public class Task {

    @Id
    private String id;

    @Field("project_id")
    private String projectId;

    @Field("title")
    private String title;

    @Field("description")
    private String description;

    @CreatedDate
    @Field("created_date")
    private Date createdDate;

    @Field("status")
    private TaskStatus status;
}



