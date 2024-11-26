package com.deesuntech.task_management_system.task.dto;

import com.deesuntech.task_management_system.task.utils.TaskPriorityEnum;
import com.deesuntech.task_management_system.task.utils.TaskStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateTaskDto {

    private Long tenantId;
    private String title;
    private String description;
    private TaskStatusEnum status;
    private TaskPriorityEnum priority;
    private LocalDateTime dueDate;
    private Boolean notificationsEnabled;

    private Long createdById;
}
