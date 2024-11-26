package com.deesuntech.task_management_system.task.dto;

import com.deesuntech.task_management_system.task.utils.TaskPriorityEnum;
import com.deesuntech.task_management_system.task.utils.TaskStatusEnum;
import com.deesuntech.task_management_system.user.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TaskDto {

    private Long id;

    private Long tenantId;

    private String title;

    private String description;

    private TaskStatusEnum status;

    private TaskPriorityEnum priority;

    private LocalDateTime dueDate;

    private LocalDateTime completedAt;

    private Boolean notificationsEnabled;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    //created_by_id
    private User createdBy;
}
