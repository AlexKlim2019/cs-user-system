package com.cs.user.system.user.service.domain;

import com.cs.user.system.user.service.domain.dto.command.CreateUserCommand;
import com.cs.user.system.user.service.domain.dto.command.DeleteUserCommand;
import com.cs.user.system.user.service.domain.dto.command.PatchUserCommand;
import com.cs.user.system.user.service.domain.dto.command.UpdateUserCommand;
import com.cs.user.system.user.service.domain.dto.query.SearchUsersQuery;
import com.cs.user.system.user.service.domain.dto.response.*;
import com.cs.user.system.user.service.domain.handler.*;
import com.cs.user.system.user.service.domain.port.input.service.UserApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserApplicationServiceImpl implements UserApplicationService {

    private final CreateUserCommandHandler createUserCommandHandler;
    private final SearchUsersQueryHandler searchUsersQueryHandler;
    private final UpdateUserCommandHandler updateUserCommandHandler;
    private final DeleteUserCommandHandler deleteUserCommandHandler;
    private final PatchUserCommandHandler patchUserCommandHandler;

    @Override
    public CreateUserResponse saveUser(CreateUserCommand command) {
        return createUserCommandHandler.save(command);
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
    public PatchUserResponse partialUpdateUser(PatchUserCommand command) {
        return patchUserCommandHandler.handle(command);
    }

    @Override
    public DeleteUserResponse deleteUser(DeleteUserCommand command) {
        return deleteUserCommandHandler.handle(command);
    }
}
