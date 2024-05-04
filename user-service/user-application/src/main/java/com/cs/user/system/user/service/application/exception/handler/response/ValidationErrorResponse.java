package com.cs.user.system.user.service.application.exception.handler.response;


import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ValidationErrorResponse {
    private final String code;
    private final List<Violation> violations = new ArrayList<>();
}
