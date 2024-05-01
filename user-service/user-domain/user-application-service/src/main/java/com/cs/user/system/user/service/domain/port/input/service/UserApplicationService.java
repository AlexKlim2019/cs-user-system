package com.cs.user.system.user.service.domain.port.input.service;

import com.cs.user.system.user.service.domain.dto.command.CreateUserCommand;
import com.cs.user.system.user.service.domain.dto.command.DeleteUserCommand;
import com.cs.user.system.user.service.domain.dto.command.UpdateUserCommand;
import com.cs.user.system.user.service.domain.dto.query.SearchUsersQuery;
import com.cs.user.system.user.service.domain.dto.response.CreateUserResponse;
import com.cs.user.system.user.service.domain.dto.response.SearchUsersResponse;
import com.cs.user.system.user.service.domain.dto.response.UpdateUserResponse;

public interface UserApplicationService {

    CreateUserResponse saveUser(CreateUserCommand command);

    SearchUsersResponse findAll(SearchUsersQuery query);

    UpdateUserResponse updateUser(UpdateUserCommand command);

    void deleteUser(DeleteUserCommand command);
}
