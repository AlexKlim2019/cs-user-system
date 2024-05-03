package com.cs.user.system.user.service.domain.mapper;

import com.cs.user.system.user.service.domain.dto.command.CreateUserCommand;
import com.cs.user.system.user.service.domain.dto.command.UpdateUserCommand;
import com.cs.user.system.user.service.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserDataMapper {

    public User createUserCommandToUser(CreateUserCommand command) {
        return User.builder()
                .firstName(command.firstName())
                .lastName(command.lastName())
                .email(command.email())
                .birthDate(command.birthDate())
                .address(command.address())
                .phoneNumber(command.phoneNumber())
                .build();
    }

    public User updateUserCommandToUser(UUID userId, UpdateUserCommand command) {
        return User.builder()
                .id(userId)
                .firstName(command.firstName())
                .lastName(command.lastName())
                .email(command.email())
                .birthDate(command.birthDate())
                .address(command.address())
                .phoneNumber(command.phoneNumber())
                .build();
    }
}
