package com.cs.user.system.user.service.domain;

import com.cs.user.system.user.service.domain.entity.User;
import com.cs.user.system.user.service.domain.event.CreateUserEvent;
import com.cs.user.system.user.service.domain.event.UpdateUserEvent;
import com.cs.user.system.user.service.domain.service.UserDomainService;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.cs.user.system.constants.DomainConstants.UTC;

@Slf4j
public class UserDomainServiceImpl implements UserDomainService {

    private final int minimumRegistrationAge;

    public UserDomainServiceImpl(int minimumRegistrationAge) {
        this.minimumRegistrationAge = minimumRegistrationAge;
    }

    @Override
    public CreateUserEvent validateAndInitiateUser(User user) {
        user.validateUser(minimumRegistrationAge);
        log.info("User with id: {} is initiated", user.getId());
        return new CreateUserEvent(user, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public UpdateUserEvent validateAndUpdateUser(User user) {
        user.validateUser(minimumRegistrationAge);
        log.info("User with id: {} is updated", user.getId());
        return new UpdateUserEvent(user, ZonedDateTime.now(ZoneId.of(UTC)));
    }
}
