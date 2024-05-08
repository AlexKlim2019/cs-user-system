package com.cs.user.system.user.service.domain.entity;

import com.cs.user.system.user.service.domain.exception.UserDomainException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.cs.user.system.constants.DomainConstants.UTC;
import static com.cs.user.system.utils.StringUtils.concatenate;

public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;

    public User(Builder builder) {
        super.setId(builder.id);
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.birthDate = builder.birthDate;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void initializeUser() {
        setId(UUID.randomUUID());
    }

    public void validateUser(int minimumRegistrationAge) {
        validateFirstName();
        validateLastName();
        validateBirthDate(minimumRegistrationAge);
    }

    private void validateFirstName() {
        if (this.firstName.isBlank()) {
            throw new UserDomainException("The first name should not be empty");
        }
    }

    private void validateLastName() {
        if (this.lastName.isBlank()) {
            throw new UserDomainException("The last name should not be empty");
        }
    }

    private void validateBirthDate(int minimumRegistrationAge) {
        int userAge = ZonedDateTime.now(ZoneId.of(UTC)).getYear() - (this.birthDate.getYear());
        if (userAge < minimumRegistrationAge) {
            var message = concatenate("User should be more than", String.valueOf(minimumRegistrationAge), "years old");
            throw new UserDomainException(message);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static final class Builder {
        private UUID id;
        private String firstName;
        private String lastName;
        private String email;
        private LocalDate birthDate;
        private String address;
        private String phoneNumber;

        private Builder() {
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
