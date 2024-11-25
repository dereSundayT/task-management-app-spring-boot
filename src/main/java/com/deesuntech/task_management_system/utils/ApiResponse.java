package com.deesuntech.task_management_system.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class ApiResponse {

    private boolean status;
    private String message;
    private Object data;
}
