package com.cs.user.system.user.service.domain.event;

import com.cs.user.system.user.service.domain.entity.User;

import java.time.LocalDateTime;

public class CreateUserEvent extends UserEvent{
    public CreateUserEvent(User user, LocalDateTime createdAt) {
        super(user, createdAt);
    }
}
