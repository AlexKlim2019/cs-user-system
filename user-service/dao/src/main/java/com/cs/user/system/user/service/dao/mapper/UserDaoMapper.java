package com.cs.user.system.user.service.dao.mapper;

import com.cs.user.system.user.service.dao.entity.UserEntity;
import com.cs.user.system.user.service.domain.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserDaoMapper {

    public UserEntity userToUserEntity(User user, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return UserEntity.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    public User userEntityToUser(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .birthDate(entity.getBirthDate())
                .address(entity.getAddress())
                .phoneNumber(entity.getPhoneNumber())
                .build();
    }
}
