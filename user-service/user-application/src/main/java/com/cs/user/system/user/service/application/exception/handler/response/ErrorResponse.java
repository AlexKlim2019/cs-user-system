package com.cs.user.system.user.service.application.exception.handler.response;


import lombok.Builder;

// TODO make it parent error response
@Builder
public record ErrorResponse(String code, String message) {
}
