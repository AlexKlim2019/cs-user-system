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

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUserCommandHandler {

    private final UserRepository userRepository;
    private final UserDataMapper userDataMapper;
    private final UserDomainService userDomainService;

    public CreateUserResponse save(CreateUserCommand command) {
        User user = userDataMapper.createUserCommandToUser(command);
        CreateUserEvent event = userDomainService.validateAndInitiateUser(user);
        userRepository.save(event.getUser(), event.getCreatedAt())
                .orElseThrow(() -> new UserDomainException("Could not save user!"));
        return new CreateUserResponse(event.getUser(), "User with has been created successfully");
    }
}
