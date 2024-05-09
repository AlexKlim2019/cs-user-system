package com.cs.user.system.user.service.dao.adapter;

import com.cs.user.system.user.service.dao.mapper.UserDaoMapper;
import com.cs.user.system.user.service.dao.repository.UserJpaRepository;
import com.cs.user.system.user.service.domain.entity.User;
import com.cs.user.system.user.service.domain.exception.UserNotFoundException;
import com.cs.user.system.user.service.domain.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository repository;
    private final UserDaoMapper mapper;
    @Override
    public User save(User user, LocalDateTime createdAt) {
        var entity = mapper.userToUserEntity(user, createdAt, createdAt);
        var savedEntity = repository.save(entity);
        return mapper.userEntityToUser(savedEntity);
    }

    @Override
    public void update(User user, LocalDateTime updatedAt) {
        var existedEntity = repository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found with given id!"));
        var entity = mapper.userToUserEntity(user, existedEntity.getCreatedAt(), updatedAt);
        repository.save(entity);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return repository.findById(id).map(mapper::userEntityToUser);
    }

    @Override
    public Optional<List<User>> findAllByBirthDateRange(LocalDate from, LocalDate to) {
        var users = repository.findAllByBirthDateBetween(from, to).stream()
                .map(mapper::userEntityToUser)
                .toList();
        return Optional.of(users);
    }

    @Override
    public void deleteById(UUID id) {
        var existedEntity = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with given id!"));
        repository.delete(existedEntity);
    }
}
