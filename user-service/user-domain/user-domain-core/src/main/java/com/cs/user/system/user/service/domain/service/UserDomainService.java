package com.cs.user.system.user.service.domain.service;

import com.cs.user.system.user.service.domain.entity.User;
import com.cs.user.system.user.service.domain.event.UserCreateEvent;
import com.cs.user.system.user.service.domain.event.UserUpdateEvent;

public interface UserDomainService {
    public UserCreateEvent validateAndInitiateUser(User user);

    public UserUpdateEvent validateAndUpdateUser(User user);
}
