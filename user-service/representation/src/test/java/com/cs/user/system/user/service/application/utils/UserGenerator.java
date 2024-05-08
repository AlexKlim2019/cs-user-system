package com.cs.user.system.user.service.application.utils;

import com.cs.user.system.user.service.domain.dto.command.CreateUserCommand;
import com.cs.user.system.user.service.domain.dto.command.PatchUserCommand;
import com.cs.user.system.user.service.domain.dto.command.UpdateUserCommand;
import com.cs.user.system.user.service.domain.dto.query.SearchUsersQuery;
import com.cs.user.system.user.service.domain.dto.response.CreateUserResponse;
import com.cs.user.system.user.service.domain.dto.response.PatchUserResponse;
import com.cs.user.system.user.service.domain.dto.response.SearchUsersResponse;
import com.cs.user.system.user.service.domain.dto.response.UpdateUserResponse;
import com.cs.user.system.user.service.domain.entity.User;

import java.util.List;

import static com.cs.user.system.user.service.application.utils.TestConstants.*;

public class UserGenerator {
    public static User generateValidUser() {
        return User.builder()
                .id(USER_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(VALID_EMAIL)
                .birthDate(BIRTH_DATE)
                .address(ADDRESS)
                .phoneNumber(PHONE_NUMBER)
                .build();
    }

    public static User generateValidUpdatedUser() {
        return User.builder()
                .id(USER_ID)
                .firstName(UPDATED_FIRST_NAME)
                .lastName(UPDATED_LAST_NAME)
                .email(UPDATED_VALID_EMAIL)
                .birthDate(UPDATED_BIRTH_DATE)
                .address(UPDATED_ADDRESS)
                .phoneNumber(UPDATED_PHONE_NUMBER)
                .build();
    }

    public static SearchUsersQuery generateValidSearchUsersQuery() {
        return SearchUsersQuery.builder()
                .from(FROM)
                .to(TO)
                .build();
    }

    public static class CommandGenerator {
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

        public static UpdateUserCommand generateValidUpdateUserCommand() {
            return UpdateUserCommand.builder()
                    .id(USER_ID)
                    .firstName(UPDATED_FIRST_NAME)
                    .lastName(UPDATED_LAST_NAME)
                    .email(UPDATED_VALID_EMAIL)
                    .birthDate(UPDATED_BIRTH_DATE)
                    .address(UPDATED_ADDRESS)
                    .phoneNumber(UPDATED_PHONE_NUMBER)
                    .build();
        }

        public static PatchUserCommand generateValidPatchUserCommand() {
            return PatchUserCommand.builder()
                    .id(USER_ID)
                    .firstName(UPDATED_FIRST_NAME)
                    .build();
        }
    }


    public static class Responses {
        public static CreateUserResponse generateSuccessCreateUserResponse() {
            return CreateUserResponse.builder()
                    .user(generateValidUser())
                    .message(CREATE_USER_RESPONSE_MESSAGE)
                    .build();
        }

        public static SearchUsersResponse generateSuccessSearchUsersResponse() {
            return SearchUsersResponse.builder()
                    .users(List.of(generateValidUser()))
                    .build();
        }

        public static UpdateUserResponse generateSuccessUpdateUserResponse() {
            return UpdateUserResponse.builder()
                    .user(generateValidUpdatedUser())
                    .message(UPDATE_USER_RESPONSE_MESSAGE)
                    .build();
        }

        public static PatchUserResponse generateSuccessPatchUserResponse() {
            var partialUpdatedUser = generateValidUser();
            partialUpdatedUser.setFirstName(UPDATED_FIRST_NAME);
            return PatchUserResponse.builder()
                    .user(partialUpdatedUser)
                    .message(PARTIAL_UPDATE_USER_RESPONSE_MESSAGE)
                    .build();
        }
    }
}
