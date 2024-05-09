package com.cs.user.system.user.service.domain;

import com.cs.user.system.user.service.domain.entity.User;
import com.cs.user.system.user.service.domain.event.CreateUserEvent;
import com.cs.user.system.user.service.domain.event.ValidateUserEvent;
import com.cs.user.system.user.service.domain.service.UserDomainService;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class UserDomainServiceImpl implements UserDomainService {

    private final int minimumRegistrationAge;

    public UserDomainServiceImpl(int minimumRegistrationAge) {
        this.minimumRegistrationAge = minimumRegistrationAge;
    }

    @Override
    public CreateUserEvent validateAndInitiateUser(User user) {
        validateUser(user);
        user.initializeUser();
        log.info("User with id: {} is initiated", user.getId());
        return new CreateUserEvent(user, LocalDateTime.now());
    }

    @Override
    public ValidateUserEvent validateUser(User user) {
        user.validateUser(minimumRegistrationAge);
        log.info("User with id: {} is validated successful", user.getId());
        return new ValidateUserEvent(user, LocalDateTime.now());
    }
}
