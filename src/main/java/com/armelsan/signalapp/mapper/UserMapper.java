package com.armelsan.signalapp.mapper;

import com.armelsan.signalapp.DTOs.UserResponse;
import com.armelsan.signalapp.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toDto(User user) {
        if (user == null) {
            return null;
        }

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        response.setDepartment(user.getDepartment());
        response.setRequiresPasswordChange(user.isRequiresPasswordChange());

        return response;
    }
}