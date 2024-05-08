package com.cs.user.system.user.service.application.utils;

import java.time.*;
import java.util.UUID;

import static com.cs.user.system.constants.DomainConstants.UTC;

public class TestConstants {

    public TestConstants() {
    }

    public final static String BAD_REQUEST_CODE = "Bad Request";
    public final static String INTERNAL_SERVER_ERROR_CODE = "Internal Server Error";


    public static final String FIRST_NAME = "Test first name";
    public static final String LAST_NAME = "Test last name";
    public static final String VALID_EMAIL = "test@mail.com";
    public static final LocalDate BIRTH_DATE = LocalDate.of(2000, Month.APRIL, 25);
    public static final String ADDRESS = "Test address";
    public static final String PHONE_NUMBER = "Test phone number";
    public static final String CREATE_USER_RESPONSE_MESSAGE = "Create user response message";

    public static final UUID USER_ID = UUID.randomUUID();
    public static final String UPDATED_FIRST_NAME = "Updated first name";
    public static final String UPDATED_LAST_NAME = "Updated last name";
    public static final String UPDATED_VALID_EMAIL = "Updated@mail.com";
    public static final LocalDate UPDATED_BIRTH_DATE = LocalDate.of(2000, Month.APRIL, 25);
    public static final String UPDATED_ADDRESS = "Updated address";
    public static final String UPDATED_PHONE_NUMBER = "Updated phone number";
    public static final String UPDATE_USER_RESPONSE_MESSAGE = "Update user response message";
    public static final String PARTIAL_UPDATE_USER_RESPONSE_MESSAGE = "Partial update user response message";

    public static final ZonedDateTime FROM = ZonedDateTime.of(LocalDateTime.of(2000, Month.AUGUST, 4, 0, 0), ZoneId.of(UTC));
    public static final ZonedDateTime TO = ZonedDateTime.of(LocalDateTime.of(2010, Month.AUGUST, 4, 0, 0), ZoneId.of(UTC));
}
