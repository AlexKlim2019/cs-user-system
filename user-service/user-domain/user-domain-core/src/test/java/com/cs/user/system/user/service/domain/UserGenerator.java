package com.cs.user.system.user.service.domain;

import com.cs.user.system.user.service.domain.entity.User;

import java.time.LocalDate;
import java.time.Month;

public class UserGenerator {

    private static final String DEFAULT_FIRST_NAME = "Test first name";
    private static final String DEFAULT_LAST_NAME = "Test last name";
    private static final String DEFAULT_EMAIL = "test@mail.com";
    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.of(2000, Month.APRIL, 25);

    public static User generateDefaultUser() {
        return User.builder()
                .firstName(DEFAULT_FIRST_NAME)
                .lastName(DEFAULT_LAST_NAME)
                .email(DEFAULT_EMAIL)
                .birthDate(DEFAULT_BIRTH_DATE)
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
