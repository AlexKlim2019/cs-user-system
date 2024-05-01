package com.cs.user.system.user.service.domain.event;

import com.cs.user.system.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public class UserUpdateEvent extends UserEvent{
    public UserUpdateEvent(User user, ZonedDateTime createdAt) {
        super(user, createdAt);
    }
}
