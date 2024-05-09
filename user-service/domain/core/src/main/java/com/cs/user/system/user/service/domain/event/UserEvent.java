package com.cs.user.system.user.service.domain.event;

import com.cs.user.system.user.service.domain.entity.User;

import java.time.LocalDateTime;

public abstract class UserEvent {
    private final User user;

    private final LocalDateTime createdAt;

    public UserEvent(User user, LocalDateTime createdAt) {
        this.user = user;
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
