package com.cs.user.system.user.service.domain.event;

import com.cs.user.system.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public class CreateUserEvent extends UserEvent{
    public CreateUserEvent(User user, ZonedDateTime createdAt) {
        super(user, createdAt);
    }
}
