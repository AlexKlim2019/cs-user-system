package com.cs.user.system.user.service.application.utils;

import java.time.LocalDate;
import java.util.Map;

import static com.cs.user.system.user.service.application.utils.TestConstants.*;
import static com.cs.user.system.user.service.application.utils.TestConstants.PHONE_NUMBER;

public class BodyMapGenerator {
    public static class CreateUserPayloadGenerator {
        public static Map<String, Object> generateCreateUserValidBodyMap() {
            return Map.of(
                    "firstName", FIRST_NAME,
                    "lastName", LAST_NAME,
                    "email", VALID_EMAIL,
                    "birthDate", BIRTH_DATE,
                    "address", ADDRESS,
                    "phoneNumber", PHONE_NUMBER
            );
        }

        public static Map<String, Object> generateCreateUserBodyMapWithoutFirstName() {
            return Map.of(
                    "lastName", LAST_NAME,
                    "email", VALID_EMAIL,
                    "birthDate", BIRTH_DATE,
                    "address", ADDRESS,
                    "phoneNumber", PHONE_NUMBER
            );
        }

        public static Map<String, Object> generateCreateUserBodyMapWithoutLastName() {
            return Map.of(
                    "firstName", FIRST_NAME,
                    "email", VALID_EMAIL,
                    "birthDate", BIRTH_DATE,
                    "address", ADDRESS,
                    "phoneNumber", PHONE_NUMBER
            );
        }

        public static Map<String, Object> generateCreateUserBodyMapWithoutEmail() {
            return Map.of(
                    "firstName", FIRST_NAME,
                    "lastName", LAST_NAME,
                    "birthDate", BIRTH_DATE,
                    "address", ADDRESS,
                    "phoneNumber", PHONE_NUMBER
            );
        }

        public static Map<String, Object> generateCreateUserBodyMapWithInvalidEmail(String email) {
            return Map.of(
                    "firstName", FIRST_NAME,
                    "lastName", LAST_NAME,
                    "email", email,
                    "birthDate", BIRTH_DATE,
                    "address", ADDRESS,
                    "phoneNumber", PHONE_NUMBER
            );
        }

        public static Map<String, Object> generateCreateUserBodyMapWithoutBirthDate() {
            return Map.of(
                    "firstName", FIRST_NAME,
                    "lastName", LAST_NAME,
                    "email", VALID_EMAIL,
                    "address", ADDRESS,
                    "phoneNumber", PHONE_NUMBER
            );
        }

        public static Map<String, Object> generateCreateUserBodyMapWithInvalidBirthDate(LocalDate birthDate) {
            return Map.of(
                    "firstName", FIRST_NAME,
                    "lastName", LAST_NAME,
                    "email", VALID_EMAIL,
                    "birthDate", birthDate,
                    "address", ADDRESS,
                    "phoneNumber", PHONE_NUMBER
            );
        }
    }

    public static class SearchUsersPayload {
        public static Map<String, Object> generateValidSearchUsersBodyMap() {
            return Map.of(
                    "from", FROM,
                    "to", TO
            );
        }

        public static Map<String, Object> generateInvalidSearchUsersBodyMap() {
            return Map.of(
                    "from", TO,
                    "to", FROM
            );
        }
    }
}
