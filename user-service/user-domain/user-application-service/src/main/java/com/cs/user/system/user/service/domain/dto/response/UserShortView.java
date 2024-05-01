package com.cs.user.system.user.service.domain.dto.response;

import lombok.Builder;
import lombok.NonNull;

import java.util.UUID;

@Builder
public record UserShortView(
        @NonNull UUID id,
        @NonNull String firstName,
        @NonNull String lastName
) {}
