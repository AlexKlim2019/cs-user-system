package com.cs.user.system.user.service.domain.dto.command;

import lombok.NonNull;

import java.time.LocalDate;

public record CreateUserCommand(
        @NonNull String firstName,
        @NonNull String lastName,
        @NonNull String email,
        @NonNull LocalDate birthDate,
        String address,
        String phoneNumber
) {}
