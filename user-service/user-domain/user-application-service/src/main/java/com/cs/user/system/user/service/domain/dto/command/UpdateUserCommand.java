package com.cs.user.system.user.service.domain.dto.command;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateUserCommand(
        @NonNull UUID id,
        @NonNull String firstName,
        @NonNull String lastName,
        @NonNull String email,
        @NonNull LocalDate birthDate,
        @NonNull String address,
        @NonNull String phoneNumber
) {}
