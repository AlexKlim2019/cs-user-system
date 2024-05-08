package com.cs.user.system.user.service.domain.utils;

import com.cs.user.system.user.service.domain.entity.User;

import java.time.LocalDate;
import java.time.Month;

public class UserDomainGenerator {

    private static final String DEFAULT_FIRST_NAME = "Test first name";
    private static final String DEFAULT_LAST_NAME = "Test last name";
    private static final String DEFAULT_EMAIL = "test@mail.com";
    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.of(2000, Month.APRIL, 25);
    private static final String DEFAULT_ADDRESS = "Test address";
    private static final String DEFAULT_PHONE_NUMBER = "Test address";

    public static User generateDefaultUser() {
        return User.builder()
                .firstName(DEFAULT_FIRST_NAME)
                .lastName(DEFAULT_LAST_NAME)
                .email(DEFAULT_EMAIL)
                .birthDate(DEFAULT_BIRTH_DATE)
                .address(DEFAULT_ADDRESS)
                .phoneNumber(DEFAULT_PHONE_NUMBER)
                .build();
    }

    public static User generateUser(String firstName, String lastName) {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(DEFAULT_EMAIL)
                .birthDate(DEFAULT_BIRTH_DATE)
                .build();
    }

    public static User generateUser(LocalDate birthDate) {
        return User.builder()
                .firstName(DEFAULT_FIRST_NAME)
                .lastName(DEFAULT_LAST_NAME)
                .email(DEFAULT_EMAIL)
                .birthDate(birthDate)
                .build();
    }
}
