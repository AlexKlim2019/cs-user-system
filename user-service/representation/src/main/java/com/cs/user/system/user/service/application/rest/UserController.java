package com.cs.user.system.user.service.application.rest;

import com.cs.user.system.user.service.domain.dto.command.CreateUserCommand;
import com.cs.user.system.user.service.domain.dto.command.DeleteUserCommand;
import com.cs.user.system.user.service.domain.dto.command.PatchUserCommand;
import com.cs.user.system.user.service.domain.dto.command.UpdateUserCommand;
import com.cs.user.system.user.service.domain.dto.query.SearchUsersQuery;
import com.cs.user.system.user.service.domain.dto.response.*;
import com.cs.user.system.user.service.domain.port.input.service.UserApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users", produces = "application/vnd.api.v1+json")
public class UserController {
    private final UserApplicationService userApplicationService;

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserCommand command) {
        log.info("User with email {} is being created...", command.email());
        var response = userApplicationService.saveUser(command);
        log.info("User with id {} has been created", response.user().getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<SearchUsersResponse> searchUsers(@Valid @RequestBody SearchUsersQuery query) {
        log.info("Users with birth date from {} to {} is being searched...", query.from(), query.to());
        var response = userApplicationService.findAll(query);
        log.info("{} users have been found", response.users().size());
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<UpdateUserResponse> updateUser(@Valid @RequestBody UpdateUserCommand command) {
        log.info("User with id {} is being updated...", command.id());
        var response = userApplicationService.updateUser(command);
        log.info("User with id {} has been updated", response.user().getId());
        return ResponseEntity.ok(response);
    }

    @PatchMapping
    public ResponseEntity<PatchUserResponse> partialUpdateUser(@RequestBody PatchUserCommand command) {
        log.info("User with id {} is being patched...", command.id());
        var response = userApplicationService.partialUpdateUser(command);
        log.info("User with id {} has been updated partially", response.user().getId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<DeleteUserResponse> deleteUser(@RequestBody DeleteUserCommand command) {
        log.info("User with id {} is being deleted...", command.id());
        var response = userApplicationService.deleteUser(command);
        log.info("User with id {} has been deleted successfully", command.id());
        return ResponseEntity.ok(response);
    }
}
