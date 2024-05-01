package com.cs.user.system.user.service.domain.event;

import com.cs.user.system.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public class UpdateUserEvent extends UserEvent{
    public UpdateUserEvent(User user, ZonedDateTime createdAt) {
        super(user, createdAt);
    }
}
