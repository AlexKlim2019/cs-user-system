package com.cs.user.system.user.service.domain.dto.command;

import lombok.NonNull;

import java.util.UUID;

public record DeleteUserCommand(@NonNull UUID id) {}
