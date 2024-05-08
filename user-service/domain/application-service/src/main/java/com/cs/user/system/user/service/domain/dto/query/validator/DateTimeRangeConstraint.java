package com.cs.user.system.user.service.domain.dto.query.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateTimeRangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateTimeRangeConstraint {

    String message() default "'From' must be less than 'To'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
