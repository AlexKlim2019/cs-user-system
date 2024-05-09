package com.cs.user.system.user.service.dao.repository;

import com.cs.user.system.user.service.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
    List<UserEntity> findAllByBirthDateBetween(LocalDate from, LocalDate to);
}
