package com.cs.user.system.user.service.domain.entity;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

public class UserGenerator {

    private static final String DEFAULT_USER_ID = "50d56be1-d6a6-4cf7-8dfc-710253292451";
    private static final String DEFAULT_FIRST_NAME = "Test first name";
    private static final String DEFAULT_LAST_NAME = "Test last name";
    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.of(2000, Month.APRIL, 25);

    public static User generateUser(String firstName, String lastName) {
        return User.builder()
                .id(UUID.fromString(DEFAULT_USER_ID))
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(DEFAULT_BIRTH_DATE)
                .build();
    }

    public static User generateUser(LocalDate birthDate) {
        return User.builder()
                .id(UUID.fromString(DEFAULT_USER_ID))
                .firstName(DEFAULT_FIRST_NAME)
                .lastName(DEFAULT_LAST_NAME)
                .birthDate(birthDate)
                .build();
    }
}
