package com.deesuntech.task_management_system.task;

import com.deesuntech.task_management_system.task.dto.CreateTaskDto;
import com.deesuntech.task_management_system.task.dto.TaskDto;
import com.deesuntech.task_management_system.task.dto.UpdateTaskDto;
import com.deesuntech.task_management_system.user.User;
import com.deesuntech.task_management_system.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class TaskServiceImplementation implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @Override
    public void createTaskService(Long tenantId, CreateTaskDto createTaskDto) {

        User user = userRepository.findById(createTaskDto.getCreatedById())
                .orElseThrow(()-> new RuntimeException("Invalid user id"));

        log.warn("I am here");
        Task task = taskMapper.createTaskDtoToTask(createTaskDto, user,tenantId);
        taskRepository.save(task);
    }

    @Override
    public TaskDto getTaskDetailService(Long tenantId, Long taskId) {
        Task task = getTask(tenantId, taskId);
        return taskMapper.taskToTaskDto(task);
    }

    @Override
    public List<TaskDto> getAllTaskService(Long tenantId) {
        return taskRepository
                .findByTenantId(tenantId)
                .stream()
                .map(taskMapper::taskToTaskDto)
                .toList();

    }

    @Override
    public void updateTaskDetailService(Long tenantId, Long taskId, UpdateTaskDto updateTaskDto) {
        Task task = getTask(tenantId, taskId);
        Task updateTaskData = taskMapper.updateTaskDtoToTask(task,updateTaskDto,tenantId);
        taskRepository.save(updateTaskData);
    }

    @Override
    public void deleteTaskDetailService(Long tenantId, Long taskId) {
        Task task = getTask(tenantId, taskId);
        taskRepository.delete(task);
    }


    private Task getTask(Long tenantId ,Long taskId){
       return taskRepository
                .findByTenantIdAndId(tenantId, taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }
}
