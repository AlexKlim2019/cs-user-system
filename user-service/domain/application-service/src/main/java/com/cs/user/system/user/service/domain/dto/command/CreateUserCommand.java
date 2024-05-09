package com.cs.user.system.user.service.domain.dto.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static com.cs.user.system.constants.DomainConstants.EMAIL_REGEX;

@Builder
public record CreateUserCommand(
        @NotBlank(message = "First name is mandatory!")
        String firstName,
        @NotBlank(message = "Last name is mandatory!")
        String lastName,
        @NotBlank(message = "Email is mandatory!")
        @Email(regexp = EMAIL_REGEX, message = "Email is not correct!")
        String email,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @Past(message = "Birth date must be earlier than current date!")
        @NotNull(message = "Birth date is mandatory!")
        LocalDate birthDate,
        String address,
        String phoneNumber
) {}
