package com.cs.user.system.user.service.presentation.utils;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

public class TestConstants {

    public TestConstants() {
    }

    public final static String BAD_REQUEST_CODE = "Bad Request";


    public static final String FIRST_NAME = "Test first name";
    public static final String LAST_NAME = "Test last name";
    public static final String VALID_EMAIL = "test@mail.com";
    public static final LocalDate BIRTH_DATE = LocalDate.of(2000, Month.APRIL, 25);
    public static final String ADDRESS = "Test address";
    public static final String PHONE_NUMBER = "Test phone number";
    public static final UUID USER_ID = UUID.randomUUID();
    public static final String UPDATED_FIRST_NAME = "Updated first name";
    public static final String UPDATED_LAST_NAME = "Updated last name";
    public static final String UPDATED_VALID_EMAIL = "Updated@mail.com";
    public static final LocalDate UPDATED_BIRTH_DATE = LocalDate.of(2000, Month.APRIL, 25);
    public static final String UPDATED_ADDRESS = "Updated address";
    public static final String UPDATED_PHONE_NUMBER = "Updated phone number";
    public static final LocalDate FROM = LocalDate.of(2000, Month.AUGUST, 4);
    public static final LocalDate TO = LocalDate.of(2010, Month.AUGUST, 4);
}
