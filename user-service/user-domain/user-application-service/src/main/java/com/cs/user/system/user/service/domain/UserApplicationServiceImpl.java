package com.cs.user.system.user.service.domain;

import com.cs.user.system.user.service.domain.dto.command.CreateUserCommand;
import com.cs.user.system.user.service.domain.dto.command.DeleteUserCommand;
import com.cs.user.system.user.service.domain.dto.command.UpdateUserCommand;
import com.cs.user.system.user.service.domain.dto.query.SearchUsersQuery;
import com.cs.user.system.user.service.domain.dto.response.CreateUserResponse;
import com.cs.user.system.user.service.domain.dto.response.SearchUsersResponse;
import com.cs.user.system.user.service.domain.dto.response.UpdateUserResponse;
import com.cs.user.system.user.service.domain.handler.CreateUserCommandHandler;
import com.cs.user.system.user.service.domain.handler.DeleteUserCommandHandler;
import com.cs.user.system.user.service.domain.handler.SearchUsersQueryHandler;
import com.cs.user.system.user.service.domain.handler.UpdateUserCommandHandler;
import com.cs.user.system.user.service.domain.port.input.service.UserApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserApplicationServiceImpl implements UserApplicationService {

    private final SearchUsersQueryHandler searchUsersQueryHandler;
    private final CreateUserCommandHandler createUserCommandHandler;
    private final UpdateUserCommandHandler updateUserCommandHandler;
    private final DeleteUserCommandHandler deleteUserCommandHandler;

    @Override
    public CreateUserResponse saveUser(CreateUserCommand command) {
        return createUserCommandHandler.handle(command);
    }

    @Override
    public SearchUsersResponse findAll(SearchUsersQuery query) {
        return searchUsersQueryHandler.handle(query);
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserCommand command) {
        return updateUserCommandHandler.handle(command);
    }

    @Override
    public void deleteUser(DeleteUserCommand command) {
        deleteUserCommandHandler.handle(command);
    }
}
