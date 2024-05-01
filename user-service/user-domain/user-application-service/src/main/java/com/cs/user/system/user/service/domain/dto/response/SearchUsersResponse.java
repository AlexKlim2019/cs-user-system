package com.cs.user.system.user.service.domain.dto.response;

import java.util.List;

public record SearchUsersResponse(List<UserShortView> users) {}
