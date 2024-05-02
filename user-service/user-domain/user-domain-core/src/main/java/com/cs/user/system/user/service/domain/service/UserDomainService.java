package com.cs.user.system.user.service.domain.service;

import com.cs.user.system.user.service.domain.entity.User;
import com.cs.user.system.user.service.domain.event.CreateUserEvent;
import com.cs.user.system.user.service.domain.event.PatchUserEvent;
import com.cs.user.system.user.service.domain.event.ValidateUserEvent;

public interface UserDomainService {
    CreateUserEvent validateAndInitiateUser(User user);

    ValidateUserEvent validateUser(User user);
}
