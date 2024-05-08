package com.cs.user.system.user.service.domain.dto.response;

import com.cs.user.system.user.service.domain.entity.User;
import lombok.NonNull;

public record PatchUserResponse(@NonNull User user, String message) {}
