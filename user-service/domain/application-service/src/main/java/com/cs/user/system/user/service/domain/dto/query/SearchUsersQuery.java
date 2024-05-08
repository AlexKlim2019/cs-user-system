package com.cs.user.system.user.service.domain.dto.query;

import com.cs.user.system.user.service.domain.dto.query.validator.DateTimeRangeConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
@DateTimeRangeConstraint
public record SearchUsersQuery(
        @NotNull(message = "From is mandatory!")
        ZonedDateTime from,
        @NotNull(message = "To is mandatory!")
        ZonedDateTime to) {
}
