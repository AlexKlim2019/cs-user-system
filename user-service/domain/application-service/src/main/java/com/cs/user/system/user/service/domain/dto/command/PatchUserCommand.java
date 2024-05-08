package com.cs.user.system.user.service.domain.dto.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

import static com.cs.user.system.constants.DomainConstants.EMAIL_REGEX;

@Builder
public record PatchUserCommand(
        @NotNull(message = "Id is mandatory!")
        UUID id,
        String firstName,
        String lastName,
        @Email(regexp = EMAIL_REGEX, message = "Email is not correct!")
        String email,
        @Past(message = "Birth date must be earlier than current date!")
        LocalDate birthDate,
        String address,
        String phoneNumber
) {}
