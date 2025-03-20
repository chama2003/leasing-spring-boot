package com.example.leasing_backend.controller;

import com.example.leasing_backend.dto.LoginRequest;
import com.example.leasing_backend.dto.UserDto;
import com.example.leasing_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // Adjust as needed for your frontend port
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    @GetMapping("{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable("username") String username) {
        UserDto userDto = userService.getUserByUsername(username);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserDto> updateUserByUsername(@PathVariable("username") String username,
                                                        @RequestBody UserDto updatedUser) {
        UserDto userDto = userService.updateUserByUsername(username, updatedUser);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyPassword(@RequestParam String username, @RequestParam String password) {
        boolean isPasswordCorrect = userService.verifyUserPassword(username, password);
        if (isPasswordCorrect) {
            return ResponseEntity.ok("Password is correct");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }
    }
    @DeleteMapping("{username}")
    public ResponseEntity<String> deleteUserByUsername(@PathVariable("username") String username) {
        userService.deleteUserByUsername(username);
        return ResponseEntity.ok("User deleted successfully");
    }


}
