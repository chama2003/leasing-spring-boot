package com.example.leasing_backend.mapper;

import com.example.leasing_backend.dto.UserDto;
import com.example.leasing_backend.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }

    public static User mapToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
user.setRole(userDto.getRole());
        // adminId is set automatically to 1
        return user;
    }
}
