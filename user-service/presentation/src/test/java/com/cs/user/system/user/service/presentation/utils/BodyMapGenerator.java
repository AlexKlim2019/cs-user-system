package com.cs.user.system.user.service.presentation.utils;

import java.time.LocalDate;
import java.util.Map;

import static com.cs.user.system.user.service.presentation.utils.TestConstants.*;
import static com.cs.user.system.user.service.presentation.utils.TestConstants.PHONE_NUMBER;

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

    public static class UpdateUserPayloadGenerator {
        public static Map<String, Object> generateUpdateUserValidBodyMap() {
            return Map.of(
                    "id", USER_ID.toString(),
                    "firstName", UPDATED_FIRST_NAME,
                    "lastName", UPDATED_LAST_NAME,
                    "email", UPDATED_VALID_EMAIL,
                    "birthDate", UPDATED_BIRTH_DATE,
                    "address", UPDATED_ADDRESS,
                    "phoneNumber", UPDATED_PHONE_NUMBER
            );
        }

        public static Map<String, Object> generateUpdateUserBodyMapWithoutId() {
            return Map.of(
                    "firstName", UPDATED_FIRST_NAME,
                    "lastName", UPDATED_LAST_NAME,
                    "email", UPDATED_VALID_EMAIL,
                    "birthDate", UPDATED_BIRTH_DATE,
                    "address", UPDATED_ADDRESS,
                    "phoneNumber", UPDATED_PHONE_NUMBER
            );
        }

        public static Map<String, Object> generateUpdateUserBodyMapWithoutFirstName() {
            return Map.of(
                    "id", USER_ID.toString(),
                    "lastName", UPDATED_LAST_NAME,
                    "email", UPDATED_VALID_EMAIL,
                    "birthDate", UPDATED_BIRTH_DATE,
                    "address", UPDATED_ADDRESS,
                    "phoneNumber", UPDATED_PHONE_NUMBER
            );
        }

        public static Map<String, Object> generateUpdateUserBodyMapWithoutLastName() {
            return Map.of(
                    "id", USER_ID.toString(),
                    "firstName", UPDATED_FIRST_NAME,
                    "email", UPDATED_VALID_EMAIL,
                    "birthDate", UPDATED_BIRTH_DATE,
                    "address", UPDATED_ADDRESS,
                    "phoneNumber", UPDATED_PHONE_NUMBER
            );
        }

        public static Map<String, Object> generateUpdateUserBodyMapWithoutEmail() {
            return Map.of(
                    "id", USER_ID.toString(),
                    "firstName", UPDATED_FIRST_NAME,
                    "lastName", UPDATED_LAST_NAME,
                    "birthDate", UPDATED_BIRTH_DATE,
                    "address", UPDATED_ADDRESS,
                    "phoneNumber", UPDATED_PHONE_NUMBER
            );
        }

        public static Map<String, Object> generateUpdateUserBodyMapWithInvalidEmail(String updatedEmail) {
            return Map.of(
                    "id", USER_ID.toString(),
                    "firstName", UPDATED_FIRST_NAME,
                    "lastName", UPDATED_LAST_NAME,
                    "email", updatedEmail,
                    "birthDate", UPDATED_BIRTH_DATE,
                    "address", UPDATED_ADDRESS,
                    "phoneNumber", UPDATED_PHONE_NUMBER
            );
        }

        public static Map<String, Object> generateUpdateUserBodyMapWithoutBirthDate() {
            return Map.of(
                    "id", USER_ID.toString(),
                    "firstName", UPDATED_FIRST_NAME,
                    "lastName", UPDATED_LAST_NAME,
                    "email", UPDATED_VALID_EMAIL,
                    "address", UPDATED_ADDRESS,
                    "phoneNumber", UPDATED_PHONE_NUMBER
            );
        }

        public static Map<String, Object> generateUpdateUserBodyMapWithInvalidBirthDate(LocalDate birthDate) {
            return Map.of(
                    "id", USER_ID.toString(),
                    "firstName", UPDATED_FIRST_NAME,
                    "lastName", UPDATED_LAST_NAME,
                    "email", UPDATED_VALID_EMAIL,
                    "birthDate", birthDate,
                    "address", UPDATED_ADDRESS,
                    "phoneNumber", UPDATED_PHONE_NUMBER
            );
        }
    }
}
