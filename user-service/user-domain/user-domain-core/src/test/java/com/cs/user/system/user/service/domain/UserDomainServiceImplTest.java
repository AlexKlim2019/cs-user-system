package com.cs.user.system.user.service.domain;

import com.cs.user.system.user.service.domain.exception.UserDomainException;
import com.cs.user.system.user.service.domain.service.UserDomainService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Month;

import static com.cs.user.system.user.service.domain.UserGenerator.generateDefaultUser;
import static com.cs.user.system.user.service.domain.UserGenerator.generateUser;
import static com.cs.user.system.utils.StringUtils.concatenate;
import static org.assertj.core.api.Assertions.*;

class UserDomainServiceImplTest {
    private static final int MINIMUM_AGE = 18;
    private final UserDomainService service = new UserDomainServiceImpl(MINIMUM_AGE);

    @Nested
    class ValidateAndInitiateUserTests {

        @Test
        void successfulScenario() {
            assertThatCode(() -> {
                var result = service.validateAndInitiateUser(generateDefaultUser());
                assertThat(result.getUser().getId()).isNotNull();
            }).doesNotThrowAnyException();
        }

        @ParameterizedTest()
        @ValueSource(strings = {"", "\n", " "})
        void givenInvalidFirstName_whenValidateAndInitiateUser_thenThrowException(String value) {
            assertThatThrownBy(() -> {
                var user = generateUser(value, "Test last name");
                service.validateAndInitiateUser(user);
            }).isInstanceOf(UserDomainException.class).hasMessage("The first name should not be empty");
        }

        @ParameterizedTest()
        @ValueSource(strings = {"", "\n", " "})
        void givenInvalidLastName_whenValidateAndInitiateUser_thenThrowException(String value) {
            assertThatThrownBy(() -> {
                var user = generateUser("Test first name", value);
                service.validateAndInitiateUser(user);
            }).isInstanceOf(UserDomainException.class).hasMessage("The last name should not be empty");
        }

        @Test
        void givenInvalidUserAge_whenValidateAndInitiateUser_thenThrowException() {
            var errorMessage = concatenate("User should be more than", String.valueOf(MINIMUM_AGE), "years old");
            assertThatThrownBy(() -> {
                var user = generateUser(LocalDate.of(2015, Month.APRIL, 24));
                service.validateAndInitiateUser(user);
            }).isInstanceOf(UserDomainException.class).hasMessage(errorMessage);
        }
    }
}
