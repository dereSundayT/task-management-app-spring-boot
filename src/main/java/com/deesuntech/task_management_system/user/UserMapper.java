package com.deesuntech.task_management_system.user;

import com.deesuntech.task_management_system.user.dto.AddUserDto;
import com.deesuntech.task_management_system.user.dto.UpdateUserDto;
import com.deesuntech.task_management_system.user.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User addUserDtoToUser(AddUserDto addUserDto) {
        return updateUserFields(new User(), addUserDto.getName(), addUserDto.getEmail());
    }

    public User updateUserDtoToUser(User user, UpdateUserDto updateUserDto) {
        return updateUserFields(user, updateUserDto.getName(), updateUserDto.getEmail());
    }

    private User updateUserFields(User user, String name, String email) {
        user.setName(name);
        user.setEmail(email);
        return user;
    }

    UserDto userToUserDto(User user) {
        return UserDto
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .status(user.getStatus())
                .build();
    }
}
