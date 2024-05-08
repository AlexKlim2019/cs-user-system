package com.cs.user.system.user.service.domain.dto.query.validator;

import com.cs.user.system.user.service.domain.dto.query.SearchUsersQuery;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateTimeRangeValidator implements ConstraintValidator<DateTimeRangeConstraint, SearchUsersQuery> {

    @Override
    public void initialize(DateTimeRangeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(SearchUsersQuery value, ConstraintValidatorContext context) {
        if (value.from() == null || value.to() == null) return false;
        return value.from().isBefore(value.to());
    }
}
