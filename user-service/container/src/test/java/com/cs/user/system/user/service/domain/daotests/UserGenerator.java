package com.cs.user.system.user.service.domain.daotests;

import com.cs.user.system.user.service.dao.entity.UserEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class UserGenerator {
    public static UserEntity generateUserEntity(UUID id, LocalDate birthDate) {
        return UserEntity.builder()
                .id(id)
                .firstName("Test first name")
                .lastName("Test last name")
                .email("test@test.com")
                .birthDate(birthDate)
                .address("Some address")
                .phoneNumber("+380973456978")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
