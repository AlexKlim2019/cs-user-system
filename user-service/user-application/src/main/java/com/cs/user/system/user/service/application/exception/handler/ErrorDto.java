package com.cs.user.system.user.service.application.exception.handler;


import lombok.Builder;

@Builder
public record ErrorDto(String code, String message) {
}
