package com.deesuntech.task_management_system.task;

import com.deesuntech.task_management_system.task.dto.CreateTaskDto;
import com.deesuntech.task_management_system.task.dto.TaskDto;
import com.deesuntech.task_management_system.task.dto.UpdateTaskDto;
import com.deesuntech.task_management_system.user.User;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task createTaskDtoToTask(CreateTaskDto createTaskDto, User user, Long tenantId) {
        return Task.builder()
                .tenantId(tenantId)
                .title(createTaskDto.getTitle())
                .description(createTaskDto.getDescription())
                .status(createTaskDto.getStatus())
                .priority(createTaskDto.getPriority())
                .dueDate(createTaskDto.getDueDate())
                .notificationsEnabled(createTaskDto.getNotificationsEnabled())
                .createdBy(user)
                .build();
    }

    // UpdateTaskDto to Task
    public Task updateTaskDtoToTask(Task task, UpdateTaskDto updateTaskDto,Long tenantId) {
        task.setTitle(updateTaskDto.getTitle());
        task.setDescription(updateTaskDto.getDescription());
        task.setStatus(updateTaskDto.getStatus());
        task.setPriority(updateTaskDto.getPriority());
        task.setDueDate(updateTaskDto.getDueDate());
        task.setNotificationsEnabled(updateTaskDto.getNotificationsEnabled());
        return task;
    }


    //DtoToEntity
    public TaskDto taskToTaskDto(Task task) {
        return TaskDto
                .builder()
                .id(task.getId())
                .tenantId(task.getTenantId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .completedAt(task.getCompletedAt())
                .notificationsEnabled(task.getNotificationsEnabled())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .createdBy(task.getCreatedBy())
                .build();
    }

}
