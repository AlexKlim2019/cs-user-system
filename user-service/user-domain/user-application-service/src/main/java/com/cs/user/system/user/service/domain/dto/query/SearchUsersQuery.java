package com.cs.user.system.user.service.domain.dto.query;

import lombok.NonNull;

import java.time.ZonedDateTime;

public record SearchUsersQuery(@NonNull ZonedDateTime from, @NonNull ZonedDateTime to) {}
