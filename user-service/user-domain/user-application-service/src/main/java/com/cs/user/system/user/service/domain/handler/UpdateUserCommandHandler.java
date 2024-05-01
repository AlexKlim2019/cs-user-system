package com.cs.user.system.user.service.domain.handler;

import com.cs.user.system.user.service.domain.dto.command.UpdateUserCommand;
import com.cs.user.system.user.service.domain.dto.response.UpdateUserResponse;
import com.cs.user.system.user.service.domain.entity.User;
import com.cs.user.system.user.service.domain.event.ValidateUserEvent;
import com.cs.user.system.user.service.domain.exception.UserNotFoundException;
import com.cs.user.system.user.service.domain.mapper.UserDataMapper;
import com.cs.user.system.user.service.domain.port.output.repository.UserRepository;
import com.cs.user.system.user.service.domain.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateUserCommandHandler {

    private final UserRepository userRepository;
    private final UserDataMapper userDataMapper;
    private final UserDomainService userDomainService;

    public UpdateUserResponse handle(UpdateUserCommand command) {
        Optional<User> result = userRepository.findById(command.id());
        if (result.isEmpty()) {
            log.error("User with id {} not found!", command.id());
            throw new UserNotFoundException("User with id {} not found!");
        }
        User user = userDataMapper.updateUserCommandToUser(command.id(), command);
        ValidateUserEvent validateUserEvent = userDomainService.validateUser(user);
        userRepository.update(user, validateUserEvent.getCreatedAt());
        return new UpdateUserResponse(validateUserEvent.getUser().getId(), "User is validated successfully");
    }
}
