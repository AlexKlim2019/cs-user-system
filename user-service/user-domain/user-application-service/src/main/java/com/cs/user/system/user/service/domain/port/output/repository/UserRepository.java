package com.cs.user.system.user.service.domain.port.output.repository;

import com.cs.user.system.user.service.domain.entity.User;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user, ZonedDateTime createdAt);

    void update(User user, ZonedDateTime updatedAt);

    Optional<User> findById(UUID id);

    Optional<List<User>> findAllByBirthDateRange(ZonedDateTime from, ZonedDateTime to);

    void deleteById(UUID id);
}
