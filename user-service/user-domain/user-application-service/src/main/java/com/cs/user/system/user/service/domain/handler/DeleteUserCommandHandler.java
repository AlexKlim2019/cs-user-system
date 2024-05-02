package com.cs.user.system.user.service.domain.handler;

import com.cs.user.system.user.service.domain.dto.command.DeleteUserCommand;
import com.cs.user.system.user.service.domain.dto.response.DeleteUserResponse;
import com.cs.user.system.user.service.domain.entity.User;
import com.cs.user.system.user.service.domain.exception.UserNotFoundException;
import com.cs.user.system.user.service.domain.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.cs.user.system.utils.StringUtils.concatenate;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteUserCommandHandler {

    private final UserRepository userRepository;

    public DeleteUserResponse handle(DeleteUserCommand command) {
        Optional<User> result = userRepository.findById(command.id());
        if (result.isEmpty()) {
            log.error("User with id {} not found!", command.id());
            throw new UserNotFoundException("User with id {} not found!");
        }
        userRepository.deleteById(command.id());
        log.info("User with id {} has been deleted successfully", command.id());
        var responseMessage = concatenate("User with id", command.id().toString(), "has been deleted successfully");
        return new DeleteUserResponse(responseMessage);
    }
}
