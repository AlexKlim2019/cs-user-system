package com.cs.user.system.user.service.domain.daotests;

import com.cs.user.system.user.service.dao.repository.UserJpaRepository;
import com.cs.user.system.user.service.domain.daotests.config.BaseJpaRepositoryTest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.UUID;

import static com.cs.user.system.user.service.domain.daotests.UserGenerator.generateUserEntity;
import static org.assertj.core.api.Assertions.assertThat;

class UserJpaRepositoryTest extends BaseJpaRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserJpaRepository repository;

    @Test
    public void successfulSearchUserEntitiesScenario() {
        var firstUserId = UUID.randomUUID();
        var secondUserId = UUID.randomUUID();
        var firstUser = generateUserEntity(firstUserId, LocalDate.of(1990, Month.AUGUST, 9));
        var secondUser = generateUserEntity(secondUserId, LocalDate.of(2000, Month.AUGUST, 9));
        repository.saveAll(List.of(firstUser, secondUser));
        entityManager.flush();

        var from = LocalDate.of(1995, 1, 1);
        var to = LocalDate.of(2001, 1, 1);
        var result = repository.findAllByBirthDateBetween(from, to);

        assertThat(result)
                .hasSize(1)
                .isEqualTo(List.of(secondUser));
    }
}