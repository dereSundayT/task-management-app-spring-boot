package com.deesuntech.task_management_system.user;

import com.deesuntech.task_management_system.user.dto.AddUserDto;
import com.deesuntech.task_management_system.user.dto.UpdateUserDto;
import com.deesuntech.task_management_system.user.dto.UserDto;
import com.deesuntech.task_management_system.user.execeptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void createNewUser(AddUserDto addUserDto) {
        if (userRepository.findByEmail(addUserDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }
        User user = userMapper.addUserDtoToUser(addUserDto);
        userRepository.save(user);
    }

    @Override
    public UserDto getUserDetails(Long id) {
        User user = findUserById(id);
        return userMapper.userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserDto)
                .toList();
    }

    @Override
    @Transactional
    public void updateUser(Long id, UpdateUserDto updateUserDto) {
        User user = findUserById(id);
        user.setEmail(updateUserDto.getEmail());
        user.setName(updateUserDto.getName());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = findUserById(id);
        user.setStatus("deleted");
        userRepository.save(user);
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserAlreadyExistsException("User not found"));
    }
}