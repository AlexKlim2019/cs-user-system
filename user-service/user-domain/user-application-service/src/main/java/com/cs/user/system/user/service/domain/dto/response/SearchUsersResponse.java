package com.cs.user.system.user.service.domain.dto.response;

import com.cs.user.system.user.service.domain.entity.User;

import java.util.List;

public record SearchUsersResponse(List<User> users) {}
