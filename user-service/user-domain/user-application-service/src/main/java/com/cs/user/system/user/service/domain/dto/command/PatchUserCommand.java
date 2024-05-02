package com.cs.user.system.user.service.domain.dto.command;

import java.time.LocalDate;
import java.util.UUID;

public record PatchUserCommand(
        UUID id,
        String firstName,
        String lastName,
        String email,
        LocalDate birthDate,
        String address,
        String phoneNumber
) {}
