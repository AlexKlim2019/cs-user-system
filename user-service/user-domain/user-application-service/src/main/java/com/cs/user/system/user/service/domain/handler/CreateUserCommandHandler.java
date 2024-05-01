package com.cs.user.system.user.service.domain.handler;

import com.cs.user.system.user.service.domain.dto.command.CreateUserCommand;
import com.cs.user.system.user.service.domain.dto.response.CreateUserResponse;
import com.cs.user.system.user.service.domain.entity.User;
import com.cs.user.system.user.service.domain.event.CreateUserEvent;
import com.cs.user.system.user.service.domain.exception.UserDomainException;
import com.cs.user.system.user.service.domain.mapper.UserDataMapper;
import com.cs.user.system.user.service.domain.port.output.repository.UserRepository;
import com.cs.user.system.user.service.domain.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUserCommandHandler {

    private final UserRepository userRepository;
    private final UserDataMapper userDataMapper;
    private final UserDomainService userDomainService;

    public CreateUserResponse handle(CreateUserCommand command) {
        User user = userDataMapper.createUserCommandToUser(command);
        CreateUserEvent createUserEvent = userDomainService.validateAndInitiateUser(user);
        handle(user, createUserEvent.getCreatedAt());
        return new CreateUserResponse(createUserEvent.getUser().getId(), "User is created successfully");
    }

    private void handle(User user, ZonedDateTime createdAt) {
        var result = userRepository.save(user, createdAt);
        if (result == null) {
            log.error("Could not save user!");
            throw new UserDomainException("Could not save user!");
        }
        log.info("User is saved with id: {}", result.getId());
    }
}
