package com.deesuntech.task_management_system.task;

import com.deesuntech.task_management_system.task.dto.CreateTaskDto;
import com.deesuntech.task_management_system.task.dto.TaskDto;
import com.deesuntech.task_management_system.task.dto.UpdateTaskDto;

import java.util.List;

public interface TaskService {

    //Create
    void createTaskService(Long tenantId,CreateTaskDto createTaskDto);
    //Read
    TaskDto getTaskDetailService(Long tenantId,Long taskId);

    List<TaskDto> getAllTaskService(Long tenantId);

    //Update
    void updateTaskDetailService(Long tenantId,Long taskId, UpdateTaskDto updateTaskDto);

    //Delete
    void deleteTaskDetailService(Long tenantId, Long taskId);
}
