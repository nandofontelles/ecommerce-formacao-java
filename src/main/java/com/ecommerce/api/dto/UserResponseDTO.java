package com.ecommerce.api.dto;

import com.ecommerce.model.User;

public record UserResponseDTO(
        Long id,
        String fullName,
        String email,
        User.UserStatus status
) {
    public static UserResponseDTO fromUser(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail(),
                user.getStatus()
        );
    }
}
