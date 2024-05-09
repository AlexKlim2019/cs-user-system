package com.cs.user.system.user.service.domain.handler;

import com.cs.user.system.user.service.domain.dto.query.SearchUsersQuery;
import com.cs.user.system.user.service.domain.dto.response.SearchUsersResponse;
import com.cs.user.system.user.service.domain.exception.UserNotFoundException;
import com.cs.user.system.user.service.domain.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchUsersQueryHandler {

    private final UserRepository userRepository;

    public SearchUsersResponse handle(SearchUsersQuery query) {
        var users = userRepository.findAllByBirthDateRange(query.from(), query.to());
        if (users.isEmpty()) {
            throw new UserNotFoundException("Users with the birth date range not found");
        }
        return new SearchUsersResponse(users);
    }
}
