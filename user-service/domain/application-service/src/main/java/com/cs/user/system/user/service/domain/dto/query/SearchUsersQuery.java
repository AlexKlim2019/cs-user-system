package com.cs.user.system.user.service.domain.dto.query;

import com.cs.user.system.user.service.domain.dto.query.validator.DateTimeRangeConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@DateTimeRangeConstraint
public record SearchUsersQuery(
        @NotNull(message = "From is mandatory!")
        LocalDate from,
        @NotNull(message = "To is mandatory!")
        LocalDate to) {
}
