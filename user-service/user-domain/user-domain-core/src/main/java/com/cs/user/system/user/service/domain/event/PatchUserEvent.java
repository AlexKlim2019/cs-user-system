package com.cs.user.system.user.service.domain.event;

import com.cs.user.system.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public class PatchUserEvent extends UserEvent{
    public PatchUserEvent(User user, ZonedDateTime createdAt) {
        super(user, createdAt);
    }
}
