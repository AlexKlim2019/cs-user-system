package com.cs.user.system.user.service.domain.port.input.service;

import com.cs.user.system.user.service.domain.dto.command.CreateUserCommand;
import com.cs.user.system.user.service.domain.dto.command.DeleteUserCommand;
import com.cs.user.system.user.service.domain.dto.command.PatchUserCommand;
import com.cs.user.system.user.service.domain.dto.command.UpdateUserCommand;
import com.cs.user.system.user.service.domain.dto.query.SearchUsersQuery;
import com.cs.user.system.user.service.domain.dto.response.*;

public interface UserApplicationService {

    CreateUserResponse saveUser(CreateUserCommand command);

    SearchUsersResponse findAll(SearchUsersQuery query);

    UpdateUserResponse updateUser(UpdateUserCommand command);

    PatchUserResponse partialUpdateUser(PatchUserCommand command);

    DeleteUserResponse deleteUser(DeleteUserCommand command);
}
