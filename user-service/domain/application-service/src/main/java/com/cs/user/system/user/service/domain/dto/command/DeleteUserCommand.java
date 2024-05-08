package com.cs.user.system.user.service.domain.dto.command;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DeleteUserCommand(@NotNull(message = "Id is mandatory!") UUID id) {}
