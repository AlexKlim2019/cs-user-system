package com.cs.user.system.user.service.domain.handler;

import com.cs.user.system.user.service.domain.dto.command.DeleteUserCommand;
import com.cs.user.system.user.service.domain.dto.response.DeleteUserResponse;
import com.cs.user.system.user.service.domain.exception.UserNotFoundException;
import com.cs.user.system.user.service.domain.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.cs.user.system.utils.StringUtils.concatenate;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteUserCommandHandler {

    private final UserRepository userRepository;

    @Transactional
    public DeleteUserResponse handle(DeleteUserCommand command) {
        userRepository.findById(command.id())
                .orElseThrow(() -> new UserNotFoundException("User with not found with given id!"));
        userRepository.deleteById(command.id());
        log.info("User with id {} has been deleted successfully", command.id());
        var responseMessage = concatenate("User with id", command.id().toString(), "has been deleted successfully");
        return new DeleteUserResponse(responseMessage);
    }
}
