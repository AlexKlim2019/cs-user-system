package com.cs.user.system.user.service.domain.event;

import com.cs.user.system.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public class ValidateUserEvent extends UserEvent{
    public ValidateUserEvent(User user, ZonedDateTime createdAt) {
        super(user, createdAt);
    }
}
