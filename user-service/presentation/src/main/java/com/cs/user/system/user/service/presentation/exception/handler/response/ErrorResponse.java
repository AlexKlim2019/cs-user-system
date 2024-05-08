package com.cs.user.system.user.service.presentation.exception.handler.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private String code;
    private String message;
}
