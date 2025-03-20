package com.example.leasing_backend.service;

import com.example.leasing_backend.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserByUsername(String username);  // Changed from ID to Username

    List<UserDto> getAllUsers();

    UserDto updateUserByUsername(String username, UserDto updatedUser);  // Changed from ID to Username

    void deleteUserByUsername(String username);  // Changed from ID to Username
    boolean verifyUserPassword(String username, String enteredPassword);

}
