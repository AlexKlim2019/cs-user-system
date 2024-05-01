package com.cs.user.system.user.service.domain.dto.response;

import lombok.NonNull;

import java.util.UUID;

public record CreateUserResponse(@NonNull UUID id, String message) {}
