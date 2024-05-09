package com.cs.user.system.user.service.domain.port.output.repository;

import com.cs.user.system.user.service.domain.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user, LocalDateTime createdAt);

    void update(User user, LocalDateTime updatedAt);

    Optional<User> findById(UUID id);

    List<User> findAllByBirthDateRange(LocalDate from, LocalDate to);

    void deleteById(UUID id);
}
