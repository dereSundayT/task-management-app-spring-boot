package com.deesuntech.task_management_system.user;

import com.deesuntech.task_management_system.user.dto.AddUserDto;
import com.deesuntech.task_management_system.user.dto.UpdateUserDto;
import com.deesuntech.task_management_system.user.dto.UserDto;

import java.util.List;

public interface UserService {

    //Create
    void createNewUser(AddUserDto addUserDto);

    //Read
    UserDto getUserDetails(Long id);

    List<UserDto> getAllUsers();

    //Update
    void updateUser(Long id, UpdateUserDto updateUserDto);

    //Delete
    void deleteUser(Long id);
}
