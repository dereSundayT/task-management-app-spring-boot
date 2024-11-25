package com.deesuntech.task_management_system.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String  name;
    private String status;


}
