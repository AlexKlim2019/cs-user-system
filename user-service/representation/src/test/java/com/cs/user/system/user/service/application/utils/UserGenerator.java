package com.cs.user.system.user.service.application.utils;

import com.cs.user.system.user.service.domain.dto.command.CreateUserCommand;
import com.cs.user.system.user.service.domain.dto.query.SearchUsersQuery;
import com.cs.user.system.user.service.domain.dto.response.CreateUserResponse;
import com.cs.user.system.user.service.domain.dto.response.SearchUsersResponse;
import com.cs.user.system.user.service.domain.entity.User;

import java.util.List;
import java.util.UUID;

import static com.cs.user.system.user.service.application.utils.TestConstants.*;

public class UserGenerator {
    public static User generateValidUser() {
        return User.builder()
                .id(UUID.randomUUID())
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(VALID_EMAIL)
                .birthDate(BIRTH_DATE)
                .address(ADDRESS)
                .phoneNumber(PHONE_NUMBER)
                .build();
    }

    public static CreateUserCommand generateValidCreateUserCommand() {
        return CreateUserCommand.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(VALID_EMAIL)
                .birthDate(BIRTH_DATE)
                .address(ADDRESS)
                .phoneNumber(PHONE_NUMBER)
                .build();
    }

    public static SearchUsersQuery generateValidSearchUsersQuery() {
        return SearchUsersQuery.builder()
                .from(FROM)
                .to(TO)
                .build();
    }


    public static class Responses {
        public static CreateUserResponse generateSuccessCreateUserResponse() {
            return CreateUserResponse.builder()
                    .user(generateValidUser())
                    .message(RESPONSE_MESSAGE)
                    .build();
        }

        public static SearchUsersResponse generateSuccessSearchUsersResponse() {
            return SearchUsersResponse.builder()
                    .users(List.of(generateValidUser()))
                    .build();
        }
    }
}
