package com.cs.user.system.user.service.domain.entity;

import com.cs.user.system.user.service.domain.exception.UserDomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Month;

import static com.cs.user.system.user.service.domain.entity.UserGenerator.generateUser;
import static com.cs.user.system.utils.StringUtils.concatenate;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("User tests")
class UserTest {
    private static final int MINIMUM_AGE = 18;

    @DisplayName("User first name validation tests")
    @Nested
    class UserFirstNameValidationTests {

        @DisplayName("Should throw exception when first name has an invalid value")
        @ParameterizedTest()
        @ValueSource(strings = {"", "\n", " "})
        void failedTests(String value) {
            assertThatThrownBy(
                    () -> generateUser(value, "Test last name").validateUser(MINIMUM_AGE)
            ).isInstanceOf(UserDomainException.class).hasMessage("The first name should not be empty");
        }

        @DisplayName("Should not throw exception when first name has an valid value")
        @Test
        void successTest() {
            var validFirstName = "Valid first name";
            assertThatCode(
                    () -> generateUser(validFirstName, "Test last name").validateUser(MINIMUM_AGE)
            ).doesNotThrowAnyException();
        }
    }

    @DisplayName("User last name validation tests")
    @Nested
    class UserLastNameValidationTests {

        @DisplayName("Should throw exception when last name has an invalid value")
        @ParameterizedTest()
        @ValueSource(strings = {"", "\n", " "})
        void failedTests(String value) {
            assertThatThrownBy(
                    () -> generateUser("Test first name", value).validateUser(MINIMUM_AGE)
            ).isInstanceOf(UserDomainException.class).hasMessage("The last name should not be empty");
        }

        @DisplayName("Should not throw exception when last name has an valid value")
        @Test
        void successTest() {
            var validLastName = "Valid last name";
            assertThatCode(
                    () -> generateUser("Test first name", validLastName).validateUser(MINIMUM_AGE)
            ).doesNotThrowAnyException();
        }
    }

    @DisplayName("User birth date validation tests")
    @Nested
    class UserBirthDateValidationTests {

        @DisplayName("Should throw exception when user is younger than 18 years old")
        @Test
        void failedTest() {
            var errorMessage = concatenate("User should be more than", String.valueOf(MINIMUM_AGE), "years old");
            assertThatThrownBy(
                    () -> generateUser(LocalDate.of(2015, Month.APRIL, 24)).validateUser(MINIMUM_AGE)
            ).isInstanceOf(UserDomainException.class).hasMessage(errorMessage);
        }

        @DisplayName("Should not throw exception when user is older than 18 years old")
        @Test
        void successTest() {
            var validBirthDate = LocalDate.of(2004, Month.AUGUST, 1);
            assertThatCode(
                    () -> generateUser(validBirthDate).validateUser(MINIMUM_AGE)
            ).doesNotThrowAnyException();
        }
    }
}
