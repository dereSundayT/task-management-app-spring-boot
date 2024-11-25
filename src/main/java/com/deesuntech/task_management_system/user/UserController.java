package com.deesuntech.task_management_system.user;


import com.deesuntech.task_management_system.user.dto.AddUserDto;
import com.deesuntech.task_management_system.user.dto.UpdateUserDto;
import com.deesuntech.task_management_system.user.dto.UserDto;
import com.deesuntech.task_management_system.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;


    /**
     * Add a new user
     *
     * @param addUserDto the details of the user to be added
     * @return ResponseEntity containing the API response
     */
    @PostMapping
    ResponseEntity<ApiResponse> addNewUser(@RequestBody AddUserDto addUserDto) {
        try {
            userService.createNewUser(addUserDto);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse(true, "User added Successfully", null));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, e.getMessage(), null));
        }
    }


    /**
     * Get all users
     *
     * @return ResponseEntity containing the API response
     */
    @GetMapping
    ResponseEntity<ApiResponse> getUsers() {
        try {
            List<UserDto> users = userService.getAllUsers();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse(true, "", users));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, e.getMessage(), null));
        }
    }

    /**
     * Get user details
     *
     * @param id the ID of the user
     * @return ResponseEntity containing the API response
     */
    @GetMapping("{id}")
    ResponseEntity<ApiResponse> getUserDetails(@PathVariable Long id) {
        try {
            UserDto userDto = userService.getUserDetails(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse(true, "", userDto));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, e.getMessage(), null));
        }
    }


    /**
     * Update user details
     *
     * @param id            the ID of the user to be updated
     * @param updateUserDto the new details of the user
     * @return ResponseEntity containing the API response
     */
    @PatchMapping("{id}")
    ResponseEntity<ApiResponse> updateUserDetails(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto) {
        try {
            userService.updateUser(id, updateUserDto);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse(true, "Update Successful", null));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, e.getMessage(), null));
        }
    }

    /**
     * Delete a user
     *
     * @param id the ID of the user to be deleted
     * @return ResponseEntity containing the API response
     */
    @DeleteMapping("{id}")
    ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse(true, "", null));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, e.getMessage(), null));
        }
    }

}
