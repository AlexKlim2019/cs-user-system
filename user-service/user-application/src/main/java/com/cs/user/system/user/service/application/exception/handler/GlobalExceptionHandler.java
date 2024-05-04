package com.cs.user.system.user.service.application.exception.handler;

import com.cs.user.system.user.service.application.exception.handler.response.ErrorResponse;
import com.cs.user.system.user.service.application.exception.handler.response.ValidationErrorResponse;
import com.cs.user.system.user.service.application.exception.handler.response.Violation;
import com.cs.user.system.user.service.domain.exception.UserDomainException;
import com.cs.user.system.user.service.domain.exception.UserNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalServerError(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ErrorResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("Unexpected error!")
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleConstraintViolationException(ConstraintViolationException exception) {
        var response = ValidationErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        for (ConstraintViolation constraintViolation : exception.getConstraintViolations()) {
            var violation = new Violation(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
            response.getViolations().add(violation);
        }
        return response;
    }

    @ResponseBody
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var response = ValidationErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            var violation = new Violation(error.getField(), error.getDefaultMessage());
            response.getViolations().add(violation);
        }
        return response;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ErrorResponse handleUserNotFoundException(UserNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(exception.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {UserDomainException.class})
    public ErrorResponse handleUserDomainException(UserDomainException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(exception.getMessage())
                .build();
    }
}
