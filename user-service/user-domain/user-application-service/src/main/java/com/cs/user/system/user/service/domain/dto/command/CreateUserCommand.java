package com.cs.user.system.user.service.domain.dto.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.NonNull;

import java.time.LocalDate;

@Builder
public record CreateUserCommand(
        @NotBlank(message = "First name is mandatory")
        String firstName,
        @NotBlank(message = "Last name is mandatory")
        String lastName,
        @Email
        String email,
        @NonNull
        LocalDate birthDate,
        String address,
        String phoneNumber
) {}
