package com.cs.user.system.user.service.domain.handler;

import com.cs.user.system.user.service.domain.dto.command.PatchUserCommand;
import com.cs.user.system.user.service.domain.dto.response.PatchUserResponse;
import com.cs.user.system.user.service.domain.entity.User;
import com.cs.user.system.user.service.domain.event.ValidateUserEvent;
import com.cs.user.system.user.service.domain.exception.UserNotFoundException;
import com.cs.user.system.user.service.domain.port.output.repository.UserRepository;
import com.cs.user.system.user.service.domain.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PatchUserCommandHandler {

    private final UserRepository userRepository;
    private final UserDomainService userDomainService;

    public PatchUserResponse handle(PatchUserCommand command) {
        Optional<User> result = userRepository.findById(command.id());
        if (result.isEmpty()) {
            log.error("User with id {} not found!", command.id());
            throw new UserNotFoundException("User with id {} not found!");
        }
        var updatedUser = partialUpdateUser(result.get(), command);
        ValidateUserEvent event = userDomainService.validateUser(updatedUser);
        userRepository.update(updatedUser, event.getCreatedAt());
        return new PatchUserResponse(event.getUser().getId(), "User has been updated partially and successfully");
    }

    private User partialUpdateUser(User user, PatchUserCommand command) {
        if (command.firstName() != null) {
            user.setFirstName(command.firstName());
        }
        if (command.lastName() != null) {
            user.setLastName(command.lastName());
        }
        if (command.email() != null) {
            user.setEmail(command.email());
        }
        if (command.birthDate() != null) {
            user.setBirthDate(command.birthDate());
        }
        if (command.address() != null) {
            user.setAddress(command.address());
        }
        if (command.phoneNumber() != null) {
            user.setPhoneNumber(command.phoneNumber());
        }
        return user;
    }
}
