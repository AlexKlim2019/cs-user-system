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
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateUserCommandHandler {

    private final UserRepository userRepository;
    private final UserDataMapper userDataMapper;
    private final UserDomainService userDomainService;

    @Transactional
    public UpdateUserResponse handle(UpdateUserCommand command) {
        userRepository.findById(command.id())
                .orElseThrow(()-> new UserNotFoundException("User not found with given id!"));
        User user = userDataMapper.updateUserCommandToUser(command.id(), command);
        ValidateUserEvent event = userDomainService.validateUser(user);
        userRepository.update(user, event.getCreatedAt());
        return new UpdateUserResponse(event.getUser(), "User has been updated successfully");
    }
}
