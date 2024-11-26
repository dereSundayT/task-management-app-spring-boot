package com.deesuntech.task_management_system.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTenantIdAndId(Long tenantId, Long taskId);

    List<Task> findByTenantId(Long tenantId);
}
