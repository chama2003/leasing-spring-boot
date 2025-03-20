package com.example.leasing_backend.service.impl;

import com.example.leasing_backend.dto.UserDto;
import com.example.leasing_backend.entity.User;
import com.example.leasing_backend.exception.ResourceNotFoundException;
import com.example.leasing_backend.hash.PasswordUtils;
import com.example.leasing_backend.mapper.UserMapper;
import com.example.leasing_backend.repository.UserRepository;
import com.example.leasing_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        String hashedPassword = PasswordUtils.hashPassword(userDto.getPassword());

        // Create the user with the hashed password
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(hashedPassword);
        user.setRole(userDto.getRole());

        // Save the user in the database
        userRepository.save(user);

        return userDto;
    }
    @Override
    public boolean verifyUserPassword(String username, String enteredPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Check if the entered password matches the stored hashed password
        return PasswordUtils.verifyPassword(enteredPassword, user.getPassword());
    }
    @Override
    @Transactional
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
        return UserMapper.mapToUserDto(user);
    }


    @Override
    @Transactional
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto updateUserByUsername(String username, UserDto updatedUser) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
       user.setRole(updatedUser.getRole());
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    @Transactional
    public void deleteUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
        userRepository.delete(user);
    }



}
