package com.cs.user.system.user.service.application.utils;

import com.cs.user.system.user.service.domain.dto.command.CreateUserCommand;
import com.cs.user.system.user.service.domain.dto.response.CreateUserResponse;
import com.cs.user.system.user.service.domain.entity.User;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.UUID;

public class UserGenerator {

    private static final String DEFAULT_FIRST_NAME = "Test first name";
    private static final String DEFAULT_LAST_NAME = "Test last name";
    private static final String DEFAULT_EMAIL = "test@mail.com";
    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.of(2000, Month.APRIL, 25);
    private static final String DEFAULT_ADDRESS = "Test address";
    private static final String DEFAULT_PHONE_NUMBER = "Test address";
    private static final String DEFAULT_RESPONSE_MESSAGE = "Test response message";


    public static CreateUserCommand generateDefaultCreateUserCommand() {
        return CreateUserCommand.builder()
                .firstName(DEFAULT_FIRST_NAME)
                .lastName(DEFAULT_LAST_NAME)
                .email(DEFAULT_EMAIL)
                .birthDate(DEFAULT_BIRTH_DATE)
                .address(DEFAULT_ADDRESS)
                .phoneNumber(DEFAULT_PHONE_NUMBER)
                .build();
    }

    public static CreateUserResponse generateDefaultCreateUserResponse() {
        var user = User.builder()
                .id(UUID.randomUUID())
                .firstName(DEFAULT_FIRST_NAME)
                .lastName(DEFAULT_LAST_NAME)
                .email(DEFAULT_EMAIL)
                .birthDate(DEFAULT_BIRTH_DATE)
                .address(DEFAULT_ADDRESS)
                .phoneNumber(DEFAULT_PHONE_NUMBER)
                .build();
        return CreateUserResponse.builder()
                .user(user)
                .message(DEFAULT_RESPONSE_MESSAGE)
                .build();
    }


    public static class BodyMapGenerator {
        public static Map<String, Object> createBodyMapWithDefaultValues() {
            return Map.of(
                    "firstName", DEFAULT_FIRST_NAME,
                    "lastName", DEFAULT_LAST_NAME,
                    "email", DEFAULT_EMAIL,
                    "birthDate", DEFAULT_BIRTH_DATE,
                    "address", DEFAULT_ADDRESS,
                    "phoneNumber", DEFAULT_PHONE_NUMBER
            );
        }

        public static Map<String, Object> createBodyMapWithoutFirstName(String firstName) {
            return Map.of(
                    "firstName", firstName,
                    "lastName", DEFAULT_LAST_NAME,
                    "email", DEFAULT_EMAIL,
                    "birthDate", DEFAULT_BIRTH_DATE,
                    "address", DEFAULT_ADDRESS,
                    "phoneNumber", DEFAULT_PHONE_NUMBER
            );
        }
    }
}
