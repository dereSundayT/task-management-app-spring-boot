package com.deesuntech.task_management_system.task;

import com.deesuntech.task_management_system.task.utils.TaskPriorityEnum;
import com.deesuntech.task_management_system.task.utils.TaskStatusEnum;
import com.deesuntech.task_management_system.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
//@RequiredArgsConstructor
@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tenantId;

    @NotBlank
    @Size(max = 100)
    private String title;

    @Size(max = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TaskStatusEnum status;

    @Enumerated(EnumType.STRING)
    private TaskPriorityEnum priority;

    @NotNull
    private LocalDateTime dueDate;


    private LocalDateTime completedAt;



    private Boolean notificationsEnabled;


    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;



    //Many Task belong to One User
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;




}

