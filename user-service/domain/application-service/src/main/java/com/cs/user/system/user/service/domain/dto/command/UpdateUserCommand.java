package com.cs.user.system.user.service.domain.dto.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

import static com.cs.user.system.constants.DomainConstants.EMAIL_REGEX;

@Builder
public record UpdateUserCommand(
        @NotNull(message = "Id is mandatory!")
        UUID id,
        @NotNull(message = "FirstName is mandatory!")
        String firstName,
        @NotNull(message = "LastName is mandatory!")
        String lastName,
        @NotBlank(message = "Email is mandatory!")
        @Email(regexp = EMAIL_REGEX, message = "Email is not correct!")
        String email,
        @NotNull(message = "Birth date is mandatory!")
        @Past(message = "Birth date must be earlier than current date!")
        LocalDate birthDate,
        String address,
        String phoneNumber
) {}
