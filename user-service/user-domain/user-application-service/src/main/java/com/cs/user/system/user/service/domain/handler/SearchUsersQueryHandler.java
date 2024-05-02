package com.cs.user.system.user.service.domain.handler;

import com.cs.user.system.user.service.domain.dto.query.SearchUsersQuery;
import com.cs.user.system.user.service.domain.dto.response.SearchUsersResponse;
import com.cs.user.system.user.service.domain.dto.response.UserShortView;
import com.cs.user.system.user.service.domain.exception.UserNotFoundException;
import com.cs.user.system.user.service.domain.mapper.UserDataMapper;
import com.cs.user.system.user.service.domain.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchUsersQueryHandler {

    private final UserRepository userRepository;
    private final UserDataMapper userDataMapper;

    public SearchUsersResponse handle(SearchUsersQuery query) {
        var result = userRepository.findAllByBirthDateRange(query.from(), query.to());
        if (result.isEmpty()) {
            log.error("Users with birth date from {} to {} not found", query.from(), query.to());
            throw new UserNotFoundException("Users with the birth date range not found");
        }
        List<UserShortView> usersView = result.get().stream()
                .map(userDataMapper::userToUserShortView)
                .toList();
        return new SearchUsersResponse(usersView);
    }
}
