package com.cs.user.system.user.service.domain.service;

import com.cs.user.system.user.service.domain.entity.User;
import com.cs.user.system.user.service.domain.event.CreateUserEvent;
import com.cs.user.system.user.service.domain.event.UpdateUserEvent;

public interface UserDomainService {
    public CreateUserEvent validateAndInitiateUser(User user);

    public UpdateUserEvent validateAndUpdateUser(User user);
}
