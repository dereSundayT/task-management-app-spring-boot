package com.deesuntech.task_management_system.task;

import com.deesuntech.task_management_system.task.dto.CreateTaskDto;
import com.deesuntech.task_management_system.task.dto.TaskDto;
import com.deesuntech.task_management_system.task.dto.UpdateTaskDto;
import com.deesuntech.task_management_system.utils.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${api.prefix}/tasks")
@Slf4j
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllTasks(@RequestParam Long tenant_id) {
        try {
            List<TaskDto> taskLists = taskService.getAllTaskService(tenant_id);
            return ResponseEntity.ok(new ApiResponse(true, "", taskLists));
        } catch (Exception e) {
            log.error("Error fetching tasks for tenant_id {}: {}", tenant_id, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, e.getMessage(), null));
        }
    }

    @GetMapping("{task_id}")
    public ResponseEntity<ApiResponse> getTaskDetails(@PathVariable Long task_id, @RequestParam Long tenant_id) {
        try {
            TaskDto taskDto = taskService.getTaskDetailService(tenant_id, task_id);
            return ResponseEntity.ok(new ApiResponse(true, "", taskDto));
        } catch (Exception e) {
            log.error("Error fetching task details for task_id {} and tenant_id {}: {}", task_id, tenant_id, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createTask(@RequestParam Long tenant_id, @RequestBody CreateTaskDto createTaskDto) {
        try {
            taskService.createTaskService(tenant_id, createTaskDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Task added Successfully", null));
        } catch (Exception e) {
            log.error("Error creating task for tenant_id {}: {}", tenant_id, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, e.getMessage(), null));
        }
    }

    @PatchMapping("{task_id}")
    public ResponseEntity<ApiResponse> updateTask(@PathVariable Long task_id, @RequestParam Long tenant_id, @RequestBody UpdateTaskDto updateTaskDto) {
        try {
            taskService.updateTaskDetailService(tenant_id, task_id, updateTaskDto);
            return ResponseEntity.ok(new ApiResponse(true, "Task updated successfully", null));
        } catch (RuntimeException e) {
            log.error("Error updating task for task_id {} and tenant_id {}: {}", task_id, tenant_id, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("{task_id}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable Long task_id, @RequestParam Long tenant_id) {
        try {
            taskService.deleteTaskDetailService(tenant_id, task_id);
            return ResponseEntity.ok(new ApiResponse(true, "Task deleted successfully", null));
        } catch (Exception e) {
            log.error("Error deleting task for task_id {} and tenant_id {}: {}", task_id, tenant_id, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, e.getMessage(), null));
        }
    }
}